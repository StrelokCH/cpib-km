package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingError;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingError;
import ch.fhnw.cpib.project.km.token.keywords.IntegerType;
import ch.fhnw.cpib.project.km.token.keywords.NotOperator;
import ch.fhnw.cpib.project.km.token.keywords.Type;
import ch.fhnw.cpib.project.km.token.symbols.AddOperator;
import ch.fhnw.cpib.project.km.token.various.Operator;

public class MonadicExpr implements IExpression {
	private final Operator operator;
	private final IExpression expression;

	public MonadicExpr(Operator operator, IExpression expression) {
		this.operator = operator;
		this.expression = expression;
	}

	@Override
	public String toString(String indent) {
		return indent + "(" + this.getClass().getSimpleName() + ", " + operator.toString() + ")\n"
				+ expression.toString(indent + "    ");
	}

	@Override
	public void addToEnvironment(Environment env, Context context) {
		env.contextMapping.put(this, context);
		expression.addToEnvironment(env, context);
	}

	@Override
	public void checkScope(Environment env) throws ScopeCheckingError {
		expression.checkScope(env);
	}

	@Override
	public Type checkType(Environment env) throws TypeCheckingError {
		Type type = expression.checkType(env);
		if (operator instanceof AddOperator) {
			if (!(type instanceof IntegerType)) {
				throw new TypeCheckingError("Cannot use minus/plus operators on a Bool. Expression is "
						+ expression.toString(""));
			}
			return type;
		}
		if (operator instanceof NotOperator) {
			if (!(type instanceof IntegerType)) {
				throw new TypeCheckingError("Cannot use not operator on a Integer. Expression is "
						+ expression.toString(""));
			}
			return type;
		}
		throw new RuntimeException("Missing type for operator " + operator + " in MonadicExpr.checkType");
	}
}
