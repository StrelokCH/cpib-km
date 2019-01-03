package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.analysis.TypePromoter;
import ch.fhnw.cpib.project.km.exceptions.ConstCheckingException;
import ch.fhnw.cpib.project.km.exceptions.InitCheckingException;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingException;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingException;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
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
	public void checkScope(Environment env) throws ScopeCheckingException {
		expression1.checkScope(env);
		expression2.checkScope(env);
	}

	@Override
	public Type checkType(Environment env) throws TypeCheckingException {
		Type type1 = expression1.checkType(env);
		Type type2 = expression2.checkType(env);
		Type finalType = TypePromoter.promote(type1, type2);
		if (finalType == null) {
			throw new TypeCheckingException(
					"type of DyadicExpression expressions don't match. They are " + type1 + " and " + type2);
		}

		if (operator instanceof AddOperator || operator instanceof MultiplicationOperator) {
			if (!(finalType instanceof IntegerType)) {
				throw new TypeCheckingException("Cannot use arithmetic operators on two Bools. Expressions are "
						+ expression1.toString("") + " and " + expression2.toString(""));
			}
			return finalType;
		}
		if (operator instanceof CAndOperator || operator instanceof COrOperator) {
			if (!(finalType instanceof Bool)) {
				throw new TypeCheckingException("Cannot use bool operators on two Integers. Expressions are "
						+ expression1.toString("") + " and " + expression2.toString(""));
			}
			return finalType;
		}
		if (operator instanceof EqualsOperator || operator instanceof NotEqualsOperator) {
			return finalType;
		}
		if (operator instanceof RelationalOperator) {
			if (!(finalType instanceof IntegerType)) {
				throw new TypeCheckingException("Cannot use relational operators on two Bools. Expressions are "
						+ expression1.toString("") + " and " + expression2.toString(""));
			}
			return new Bool();
		}
		throw new RuntimeException("missing operator " + operator + " in DyadicExpr.checkType");
	}

	@Override
	public void checkInit(Environment env) throws InitCheckingException {
		// TODO Auto-generated method stub

	}

}
