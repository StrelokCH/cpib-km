package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.analysis.SymbolTable;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingError;

public interface IDecl {
	public String toString(String indent);

	public void appendSymbol(SymbolTable symbolTable, boolean localScope) throws ScopeCheckingError;

	public void addToEnvironment(Environment env, Context context) throws ScopeCheckingError;
}