package ch.fhnw.cpib.project.km.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingError;
import ch.fhnw.cpib.project.km.syntax.abst.FullIdentifier;
import ch.fhnw.cpib.project.km.syntax.abst.RoutineDecl;

public class SymbolTable {
	private final Map<String, FullIdentifier> localVariables = new HashMap<>();
	private final Map<String, FullIdentifier> globalVariables = new HashMap<>();
	public final List<RoutineDecl> procedures = new ArrayList<>();
	public final List<RoutineDecl> functions = new ArrayList<>();

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

	public void clearVariables() {
		localVariables.clear();
		globalVariables.clear();
	}

	public SymbolTable clone() {
		SymbolTable ret = new SymbolTable();
		ret.localVariables.putAll(localVariables);
		ret.globalVariables.putAll(globalVariables);
		ret.procedures.addAll(procedures);
		ret.functions.addAll(functions);
		return ret;
	}
}
