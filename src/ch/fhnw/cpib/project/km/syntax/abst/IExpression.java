package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.exceptions.RoutineMatchError;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingError;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingError;
import ch.fhnw.cpib.project.km.token.keywords.Type;

public interface IExpression {
	public String toString(String indent);

	public void addToEnvironment(Environment env, Context context);

	public void checkScope(Environment env) throws ScopeCheckingError;
	
	public default boolean isLValue() {
		return false;
	}

	public Type checkType(Environment env) throws TypeCheckingError;
}
