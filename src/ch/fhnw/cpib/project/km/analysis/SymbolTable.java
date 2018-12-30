package ch.fhnw.cpib.project.km.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.fhnw.cpib.project.km.exceptions.RoutineMatchError;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingError;
import ch.fhnw.cpib.project.km.syntax.abst.FullIdentifier;
import ch.fhnw.cpib.project.km.syntax.abst.FunctionCallExpr;
import ch.fhnw.cpib.project.km.syntax.abst.ProcCallCmd;
import ch.fhnw.cpib.project.km.syntax.abst.RoutineDecl;
import ch.fhnw.cpib.project.km.token.keywords.Type;

public class SymbolTable {
	private final Environment env;
	private final Map<String, FullIdentifier> localVariables = new HashMap<>();
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

	public boolean containsLocal(FullIdentifier fullIdentifier) {
		return localVariables.containsKey(fullIdentifier.getIdentifierName());
	}

	public boolean containsGlobal(FullIdentifier fullIdentifier) {
		return globalVariables.containsKey(fullIdentifier.getIdentifierName());
	}

	public void addVariable(FullIdentifier fullIdentifier, boolean local) throws ScopeCheckingError {
		String identifier = fullIdentifier.getIdentifierName();

		if (localVariables.containsKey(identifier) || globalVariables.containsKey(identifier)) {
			throw new ScopeCheckingError("identifier " + identifier + " is declared more than once");
		}

		if (local) {
			localVariables.put(identifier, fullIdentifier);
		} else {
			globalVariables.put(identifier, fullIdentifier);
		}
	}
	
	public Type getTypeForLocalVariable(String key) {
		return localVariables.get(key).getType();
	}

	public void clearVariables() {
		localVariables.clear();
		globalVariables.clear();
	}

	public SymbolTable clone() {
		SymbolTable ret = new SymbolTable(env);
		ret.localVariables.putAll(localVariables);
		ret.globalVariables.putAll(globalVariables);
		ret.procedures.addAll(procedures);
		ret.functions.addAll(functions);
		return ret;
	}

	public RoutineDecl findMatch(ProcCallCmd procCallCmd) throws RoutineMatchError {
		return new RoutineMatcher(env).findMatch(procedures, procCallCmd);
	}

	public RoutineDecl findMatch(FunctionCallExpr functionCallExpr) throws RoutineMatchError {
		return new RoutineMatcher(env).findMatch(functions, functionCallExpr);
	}
}
