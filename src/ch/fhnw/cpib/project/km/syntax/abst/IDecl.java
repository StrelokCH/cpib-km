package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.analysis.SymbolTable;
import ch.fhnw.cpib.project.km.exceptions.ConstCheckingException;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingException;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingException;

public interface IDecl {
	public String toString(String indent);

	public void appendSymbol(SymbolTable symbolTable, boolean localScope) throws ScopeCheckingException;

	public void addToEnvironment(Environment env, Context context) throws ScopeCheckingException;

	public void checkScope(Environment env) throws ScopeCheckingException;

	public void checkType(Environment env) throws TypeCheckingException;

	public void checkConst(Environment env) throws ConstCheckingException;
	
}