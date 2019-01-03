package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.analysis.SymbolTable;
import ch.fhnw.cpib.project.km.exceptions.ConstCheckingException;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingException;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingException;

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
	public void appendSymbol(SymbolTable symbolTable, boolean localScope) throws ScopeCheckingException {
		symbolTable.addVariable(typedIdentifier, localScope);
	}

	@Override
	public void addToEnvironment(Environment env, Context context) {
		// not needed
	}

	@Override
	public void checkScope(Environment env) throws ScopeCheckingException {
		// not needed
	}

	@Override
	public void checkType(Environment env) throws TypeCheckingException {
		// not needed
	}

	@Override
	public void checkConst(Environment env) throws ConstCheckingException {
		// not needed
	}
	
	

}
