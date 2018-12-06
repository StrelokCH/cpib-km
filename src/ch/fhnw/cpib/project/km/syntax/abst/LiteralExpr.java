package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.token.various.Literal;

public class LiteralExpr implements IExpression {
	private final Literal literal;

	public LiteralExpr(Literal literal) {
		this.literal = literal;
	}

	@Override
	public String toString(String indent) {
		return indent + "(" + this.getClass().getSimpleName() + ", " + literal.toString() + ")\n";
	} 
}
