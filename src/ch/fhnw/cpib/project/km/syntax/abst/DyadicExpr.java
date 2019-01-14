package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.analysis.TypePromoter;
import ch.fhnw.cpib.project.km.exceptions.CodeGenerationException;
import ch.fhnw.cpib.project.km.exceptions.ConstCheckingException;
import ch.fhnw.cpib.project.km.exceptions.InitCheckingException;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingException;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingException;
import ch.fhnw.cpib.project.km.synthesis.CodeGenerationEnvironment;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.Operator;
import ch.fhnw.cpib.project.km.vm.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.project.km.vm.IInstructions;
import ch.fhnw.cpib.project.km.vm.IInstructions.DivTruncInt;

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
					"Type of DyadicExpression expressions don't match. They are " + type1 + " and " + type2);
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
			// always works
			return new Bool();
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
		// Todo
	}

	@Override
	public void createCode(CodeGenerationEnvironment cgenv) throws CodeTooSmallError, CodeGenerationException {
		Type type1 = expression1.getTypeSafe(cgenv.env);
		Type type2 = expression2.getTypeSafe(cgenv.env);
		Type finalType = TypePromoter.promote(type1, type2);

		// load both expressions to stack

		// load both expressions
		if (finalType instanceof Int32 || finalType instanceof Bool) {
			if (operator instanceof CAndOperator || operator instanceof COrOperator) {
				// special case
			} else {
				// Both int32 or bool
				expression1.createCode(cgenv);
				expression2.createCode(cgenv);
			}
		} else if (finalType instanceof Int64) {
			expression1.createCode(cgenv);
			if (type1 instanceof Int32) {
				// promote
				cgenv.code.put(cgenv.locInc(), new IInstructions.PromoteInt32ToInt64());
			}
			expression2.createCode(cgenv);
			if (type2 instanceof Int32) {
				// promote
				cgenv.code.put(cgenv.locInc(), new IInstructions.PromoteInt32ToInt64());
			}
		} else {
			throw new CodeGenerationException(
					"Missing type " + finalType.toString() + " with operator " + operator.toString() + ".");
		}

		if (finalType instanceof Bool) {
			createCodeBool(cgenv);
		} else if (finalType instanceof Int32) {
			createCodeInt32(cgenv);
		} else if (finalType instanceof Int64) {
			createCodeInt64(cgenv);
		}
	}

	private void createCodeBool(CodeGenerationEnvironment cgenv) throws CodeTooSmallError, CodeGenerationException {
		// rel operator
		if (operator instanceof CAndOperator) {
			/*
			// this code pollutes the stack as two bools stay on the stack?

			expression1.createCode(cgenv);
			
			// jump location
			int loc1 = cgenv.locInc();

			expression2.createCode(cgenv);

			int loc3 = cgenv.locInc();
			cgenv.code.put(loc3, new IInstructions.UncondJump(loc3+2));
			
			int loc4 = cgenv.loc();
			// load bool false to stack
			cgenv.code.put(loc4, new IInstructions.LoadImInt(0));
			cgenv.code.put(loc1, new IInstructions.CondJump(loc4));*/

			// own, hopefully improved code
			expression1.createCode(cgenv);
			
			// jump location
			int loc1 = cgenv.locInc();

			// remove value of expression1 on stack
			// only gets called if expression1=true
			cgenv.code.put(cgenv.locInc(), new IInstructions.PopSingleBlock());
			
			expression2.createCode(cgenv);
			
			int loc4 = cgenv.loc();
			cgenv.code.put(loc1, new IInstructions.CondJump(loc4));
		} else if (operator instanceof COrOperator) {

			expression1.createCode(cgenv);
			
			int loc1 = cgenv.locInc();
			cgenv.code.put(loc1, new IInstructions.CondJump(loc1+2));
			
			// jump location
			int loc2 = cgenv.locInc();

			// remove value of expression1 on stack
			// only gets called if expression1=false
			cgenv.code.put(cgenv.locInc(), new IInstructions.PopSingleBlock());
			
			expression2.createCode(cgenv);

			int loc3 = cgenv.loc();
			cgenv.code.put(loc2, new IInstructions.UncondJump(loc3));
		} else {
			// default case as bools are represented as int
			createCodeInt32(cgenv);
		}
	}

	private void createCodeInt32(CodeGenerationEnvironment cgenv) throws CodeTooSmallError, CodeGenerationException {

		// add operator
		if (operator instanceof MinusOperator) {
			cgenv.code.put(cgenv.locInc(), new IInstructions.SubInt());
		} else if (operator instanceof PlusOperator) {
			cgenv.code.put(cgenv.locInc(), new IInstructions.AddInt());
		}
		// mult operator
		else if (operator instanceof DivisionOperator) {
			cgenv.code.put(cgenv.locInc(), new IInstructions.DivTruncInt());
		} else if (operator instanceof ModuloOperator) {
			cgenv.code.put(cgenv.locInc(), new IInstructions.ModTruncInt());
		} else if (operator instanceof TimesOperator) {
			cgenv.code.put(cgenv.locInc(), new IInstructions.MultInt());
		}
		// rel operator
		else if (operator instanceof EqualsOperator) {
			cgenv.code.put(cgenv.locInc(), new IInstructions.EqInt());
		} else if (operator instanceof GreaterEqualsOperator) {
			cgenv.code.put(cgenv.locInc(), new IInstructions.GeInt());
		} else if (operator instanceof GreaterThanOperator) {
			cgenv.code.put(cgenv.locInc(), new IInstructions.GtInt());
		} else if (operator instanceof LowerEqualsOperator) {
			cgenv.code.put(cgenv.locInc(), new IInstructions.LeInt());
		} else if (operator instanceof LowerThanOperator) {
			cgenv.code.put(cgenv.locInc(), new IInstructions.LtInt());
		} else if (operator instanceof NotEqualsOperator) {
			cgenv.code.put(cgenv.locInc(), new IInstructions.NeInt());
		} else {
			throw new CodeGenerationException(
					"Missing operator " + operator.toString() + " in DyadicExpression.createCodeInt32.");
		}
	}

	private void createCodeInt64(CodeGenerationEnvironment cgenv) throws CodeTooSmallError, CodeGenerationException {

		// add operator
		if (operator instanceof MinusOperator) {
			cgenv.code.put(cgenv.locInc(), new IInstructions.SubInt64());
		} else if (operator instanceof PlusOperator) {
			cgenv.code.put(cgenv.locInc(), new IInstructions.AddInt64());
		}
		// mult operator
		else if (operator instanceof DivisionOperator) {
			cgenv.code.put(cgenv.locInc(), new IInstructions.DivTruncInt64());
		} else if (operator instanceof ModuloOperator) {
			cgenv.code.put(cgenv.locInc(), new IInstructions.ModTruncInt64());
		} else if (operator instanceof TimesOperator) {
			cgenv.code.put(cgenv.locInc(), new IInstructions.MultInt64());
		}
		// rel operator
		else if (operator instanceof EqualsOperator) {
			cgenv.code.put(cgenv.locInc(), new IInstructions.EqInt64());
		} else if (operator instanceof GreaterEqualsOperator) {
			cgenv.code.put(cgenv.locInc(), new IInstructions.GeInt64());
		} else if (operator instanceof GreaterThanOperator) {
			cgenv.code.put(cgenv.locInc(), new IInstructions.GtInt64());
		} else if (operator instanceof LowerEqualsOperator) {
			cgenv.code.put(cgenv.locInc(), new IInstructions.LeInt64());
		} else if (operator instanceof LowerThanOperator) {
			cgenv.code.put(cgenv.locInc(), new IInstructions.LtInt64());
		} else if (operator instanceof NotEqualsOperator) {
			cgenv.code.put(cgenv.locInc(), new IInstructions.NeInt64());
		} else {
			throw new CodeGenerationException(
					"Missing operator " + operator.toString() + " in DyadicExpression.createCodeInt64.");
		}
	}
}
