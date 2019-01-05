package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingException;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingException;
import ch.fhnw.cpib.project.km.synthesis.CodeGenerationEnvironment;
import ch.fhnw.cpib.project.km.vm.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.project.km.exceptions.ConstCheckingException;
import ch.fhnw.cpib.project.km.exceptions.InitCheckingException;
import ch.fhnw.cpib.project.km.exceptions.AliasingCheckingException;
import ch.fhnw.cpib.project.km.exceptions.CodeGenerationException;

public interface ICommand {
	public String toString(String indent);

	public void addToEnvironment(Environment env, Context context);

	public void checkScope(Environment env) throws ScopeCheckingException;

	public void checkType(Environment env) throws TypeCheckingException;
	
	public void checkConst(Environment env) throws ConstCheckingException;
	
	public void checkInit(Environment env) throws InitCheckingException;
	
	public void checkAliasing(Environment env) throws AliasingCheckingException;

	public void createCode(CodeGenerationEnvironment cgenv) throws CodeTooSmallError, CodeGenerationException;
}
