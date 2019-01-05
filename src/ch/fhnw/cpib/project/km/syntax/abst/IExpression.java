package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.exceptions.CodeGenerationException;
import ch.fhnw.cpib.project.km.exceptions.ConstCheckingException;
import ch.fhnw.cpib.project.km.exceptions.RoutineMatchException;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingException;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingException;
import ch.fhnw.cpib.project.km.synthesis.CodeGenerationEnvironment;
import ch.fhnw.cpib.project.km.exceptions.InitCheckingException;
import ch.fhnw.cpib.project.km.token.keywords.Type;
import ch.fhnw.cpib.project.km.vm.ICodeArray.CodeTooSmallError;

public interface IExpression {
	public String toString(String indent);

	public void addToEnvironment(Environment env, Context context);

	public void checkScope(Environment env) throws ScopeCheckingException;

	public default boolean isLValue() {
		return false;
	}

	public default boolean isConst(Environment env) {
		return true;
	}

	public Type checkType(Environment env) throws TypeCheckingException;
	
	public default Type getTypeSafe(Environment env) {
		try {
			return checkType(env);
		} catch (TypeCheckingException e) {
			return null;
		}
	}

	public void checkInit(Environment env) throws InitCheckingException;

	public default void createCodeLoadAddr(CodeGenerationEnvironment cgenv)
			throws CodeTooSmallError, CodeGenerationException {
		throw new CodeGenerationException("illegal call to createCodeLoadAddr on " + this.toString(""));
	}

	public void createCode(CodeGenerationEnvironment cgenv) throws CodeTooSmallError, CodeGenerationException;

}
