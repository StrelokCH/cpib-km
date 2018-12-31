package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.exceptions.ConstCheckingError;
import ch.fhnw.cpib.project.km.exceptions.InitCheckingError;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingError;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingError;
import ch.fhnw.cpib.project.km.token.keywords.Bool;
import ch.fhnw.cpib.project.km.token.keywords.BoolLiteral;
import ch.fhnw.cpib.project.km.token.keywords.Int32;
import ch.fhnw.cpib.project.km.token.keywords.Int64;
import ch.fhnw.cpib.project.km.token.keywords.Type;
import ch.fhnw.cpib.project.km.token.various.Int32Literal;
import ch.fhnw.cpib.project.km.token.various.Int64Literal;
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

	@Override
	public void addToEnvironment(Environment env, Context context) {
		env.contextMapping.put(this, context);
	}

	@Override
	public void checkScope(Environment env) throws ScopeCheckingError {
		// not needed
	}

	@Override
	public Type checkType(Environment env) throws TypeCheckingError {
		if (literal instanceof Int32Literal) {
			return new Int32();
		}
		if (literal instanceof Int64Literal) {
			return new Int64();
		}
		if (literal instanceof BoolLiteral) {
			return new Bool();
		}
		throw new RuntimeException("Missing type for literal " + literal + " in LiteralExpr.checkType");
	}

	@Override
	public void checkInit(Environment env) throws InitCheckingError {
		// TODO Auto-generated method stub

	}

}
