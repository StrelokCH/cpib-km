package ch.fhnw.cpib.project.km.syntax.abst;

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
				+ expression.toString(indent + "    \n");
	}
}
