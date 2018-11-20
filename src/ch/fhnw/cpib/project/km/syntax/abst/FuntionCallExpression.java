package ch.fhnw.cpib.project.km.syntax.abst;

import org.openjdk.tools.javac.util.List;

import ch.fhnw.cpib.project.km.token.various.Identifier;

public class FuntionCallExpression implements IExpression {
	private final Identifier identifier;
	private final List<IExpression> expressions;

	public FuntionCallExpression(Identifier identifier, List<IExpression> expressions) {
		this.identifier = identifier;
		this.expressions = expressions;
	}

	@Override
	public String toString(String indent) {
		String ret = indent + "(" + this.getClass().getSimpleName() + ", " + identifier.toString() + ")\n";
		for (IExpression expr : expressions) {
			ret += expr.toString(indent + "    \n");
		}
		return ret;
	}
}
