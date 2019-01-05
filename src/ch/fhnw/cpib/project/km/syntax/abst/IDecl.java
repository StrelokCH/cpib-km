package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.analysis.SymbolTable;
import ch.fhnw.cpib.project.km.exceptions.AliasingCheckingException;
import ch.fhnw.cpib.project.km.exceptions.CodeGenerationException;
import ch.fhnw.cpib.project.km.exceptions.ConstCheckingException;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingException;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingException;
import ch.fhnw.cpib.project.km.synthesis.CodeGenerationEnvironment;
import ch.fhnw.cpib.project.km.vm.ICodeArray.CodeTooSmallError;

public interface IDecl {
	public String toString(String indent);

	public void appendSymbol(SymbolTable symbolTable, boolean localScope) throws ScopeCheckingException;

	public void addToEnvironment(Environment env, Context context) throws ScopeCheckingException;

	public void checkScope(Environment env) throws ScopeCheckingException;

	public void checkType(Environment env) throws TypeCheckingException;

	public void checkConst(Environment env) throws ConstCheckingException;

	public void checkAliasing(Environment env) throws AliasingCheckingException;

	public void createCode(CodeGenerationEnvironment cgenv) throws CodeTooSmallError, CodeGenerationException;
}