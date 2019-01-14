package ch.fhnw.cpib.project.km.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.fhnw.cpib.project.km.exceptions.CodeGenerationException;
import ch.fhnw.cpib.project.km.exceptions.RoutineMatchException;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingException;
import ch.fhnw.cpib.project.km.syntax.abst.FullIdentifier;
import ch.fhnw.cpib.project.km.syntax.abst.FunctionCallExpr;
import ch.fhnw.cpib.project.km.syntax.abst.ProcCallCmd;
import ch.fhnw.cpib.project.km.syntax.abst.RoutineDecl;
import ch.fhnw.cpib.project.km.token.keywords.Changemode;
import ch.fhnw.cpib.project.km.token.keywords.Const;

public class SymbolTable {
	private final Environment env;
	private final Map<String, FullIdentifier> localVariables = new HashMap<>();
	private final Map<String, Integer> localVariablesLocation = new HashMap<>();
	private final Map<String, FullIdentifier> globalVariables = new HashMap<>();
	private final List<RoutineDecl> procedures = new ArrayList<>();
	private final List<RoutineDecl> functions = new ArrayList<>();

	public SymbolTable(Environment env) {
		this.env = env;
	}

	public void addRoutine(RoutineDecl routine) {
		if (routine.isProcedure()) {
			procedures.add(routine);
		} else {
			functions.add(routine);
		}
	}

	public boolean contains(FullIdentifier fullIdentifier) {
		return containsLocal(fullIdentifier) || containsGlobal(fullIdentifier);
	}

	public boolean contains(Map<String, FullIdentifier> variables, FullIdentifier fullIdentifier) {
		String key = fullIdentifier.getIdentifierName();
		if (!variables.containsKey(key)) {
			return false;
		}

		// removed as Globals.iml implies that this check is wrong
		/*
		 * FullIdentifier declaration = variables.get(key); if
		 * (declaration.getChangemode() instanceof Const &&
		 * (fullIdentifier.getChangemode() != null && !(fullIdentifier.getChangemode()
		 * instanceof Const))) { // trying to remove const from store return false; }
		 */
		return true;
	}

	public boolean containsLocal(FullIdentifier fullIdentifier) {
		return contains(localVariables, fullIdentifier);
	}

	public boolean containsGlobal(FullIdentifier fullIdentifier) {
		return contains(globalVariables, fullIdentifier);
	}

	public void addVariable(FullIdentifier fullIdentifier, boolean local) throws ScopeCheckingException {
		String identifier = fullIdentifier.getIdentifierName();

		if (localVariables.containsKey(identifier) || globalVariables.containsKey(identifier)) {
			throw new ScopeCheckingException("identifier " + identifier + " is declared more than once");
		}

		if (local) {
			localVariables.put(identifier, fullIdentifier);
		} else {
			globalVariables.put(identifier, fullIdentifier);
		}
	}

	public FullIdentifier getDeclaration(FullIdentifier fullIdentifier) {
		String key = fullIdentifier.getIdentifierName();
		if (localVariables.containsKey(key)) {
			return localVariables.get(key);
		}
		if (globalVariables.containsKey(key)) {
			return globalVariables.get(key);
		}
		return null;
	}

	public void clearVariables() {
		localVariables.clear();
		localVariablesLocation.clear();
		globalVariables.clear();
	}

	public SymbolTable clone() {
		SymbolTable ret = new SymbolTable(env);
		ret.localVariables.putAll(localVariables);
		ret.localVariablesLocation.putAll(localVariablesLocation);
		ret.globalVariables.putAll(globalVariables);
		ret.procedures.addAll(procedures);
		ret.functions.addAll(functions);
		return ret;
	}

	public RoutineDecl findMatch(ProcCallCmd procCallCmd) throws RoutineMatchException {
		return new RoutineMatcher(env).findMatch(procedures, procCallCmd);
	}

	public RoutineDecl findMatch(FunctionCallExpr functionCallExpr) throws RoutineMatchException {
		return new RoutineMatcher(env).findMatch(functions, functionCallExpr);
	}

	public int getLocalVariablesLocation(String identifier) {
		return localVariablesLocation.getOrDefault(identifier, -1);
	}

	public void setLocalVariablesLocation(String identifier, int location) throws CodeGenerationException {
		if (localVariablesLocation.containsKey(identifier) && localVariablesLocation.get(identifier) != location) {
			throw new CodeGenerationException("Trying to add identifier " + identifier + " with a different location.");
		}
		localVariablesLocation.put(identifier, location);
	}
}
