package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.exceptions.ConstCheckingException;
import ch.fhnw.cpib.project.km.exceptions.RoutineMatchException;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingException;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingException;
import ch.fhnw.cpib.project.km.exceptions.InitCheckingException;
import ch.fhnw.cpib.project.km.token.keywords.Type;

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

	public void checkInit(Environment env) throws InitCheckingException;

}
