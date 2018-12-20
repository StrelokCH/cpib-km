package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingError;
import ch.fhnw.cpib.project.km.token.various.Operator;

public class DyadicExpr implements IExpression {
	private final Operator operator;
	private final IExpression expression1;
	private final IExpression expression2;

	public DyadicExpr(Operator operator, IExpression expression1, IExpression expression2) {
		this.operator = operator;
		this.expression1 = expression1;
		this.expression2 = expression2;
	}

	@Override
	public String toString(String indent) {
		return indent + "(" + this.getClass().getSimpleName() + ", " + operator.toString() + ")\n"
				+ expression1.toString(indent + "    ") + expression2.toString(indent + "    ");
	}

	@Override
	public void addToEnvironment(Environment env, Context context) {
		env.contextMapping.put(this, context);
		expression1.addToEnvironment(env, context);
		expression2.addToEnvironment(env, context);
	}

	@Override
	public void checkScope(Environment env) throws ScopeCheckingError {
		expression1.checkScope(env);
		expression2.checkScope(env);
	}
}
