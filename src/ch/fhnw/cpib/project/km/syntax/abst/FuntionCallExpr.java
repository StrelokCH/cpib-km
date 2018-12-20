package ch.fhnw.cpib.project.km.syntax.abst;

import java.util.List;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.token.various.Identifier;

public class FuntionCallExpr implements IExpression {
	private final Identifier identifier;
	private final List<IExpression> expressions;

	public FuntionCallExpr(Identifier identifier, List<IExpression> expressions) {
		this.identifier = identifier;
		this.expressions = expressions;
	}

	@Override
	public String toString(String indent) {
		String ret = indent + "(" + this.getClass().getSimpleName() + ", " + identifier.toString() + ")\n";
		for (IExpression expr : expressions) {
			ret += expr.toString(indent + "    ") + "\n";
		}
		return ret;
	}
	
	@Override
	public void addToEnvironment(Environment env, Context context) {
		env.contextMapping.put(this, context);
		for (IExpression expression : expressions) {
			expression.addToEnvironment(env, context);
		}
	}
}
