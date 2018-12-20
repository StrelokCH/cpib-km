package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.analysis.SymbolTable;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingError;

public class StoDecl implements IDecl {
	private final FullIdentifier typedIdentifier;

	public StoDecl(FullIdentifier typedIdentifier) {
		this.typedIdentifier = typedIdentifier;
	}

	public String toString(String indent) {
		String ret = indent + "(" + this.getClass().getSimpleName() + ")\n";
		ret += typedIdentifier.toString(indent + "    ");
		return ret;
	}
	
	public FullIdentifier getFullIdentifier() {
		return typedIdentifier;
	}

	@Override
	public void appendSymbol(SymbolTable symbolTable, boolean localScope) throws ScopeCheckingError {
		symbolTable.addVariable(typedIdentifier, localScope);
	}

	@Override
	public void addToEnvironment(Environment env, Context context) {
		// not needed
	}

}
