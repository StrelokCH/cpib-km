package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.exceptions.CodeGenerationException;
import ch.fhnw.cpib.project.km.exceptions.ConstCheckingException;
import ch.fhnw.cpib.project.km.exceptions.InitCheckingException;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingException;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingException;
import ch.fhnw.cpib.project.km.synthesis.CodeGenerationEnvironment;
import ch.fhnw.cpib.project.km.token.keywords.Int32;
import ch.fhnw.cpib.project.km.token.keywords.Int64;
import ch.fhnw.cpib.project.km.token.keywords.IntegerType;
import ch.fhnw.cpib.project.km.token.keywords.NotOperator;
import ch.fhnw.cpib.project.km.token.keywords.Type;
import ch.fhnw.cpib.project.km.token.symbols.AddOperator;
import ch.fhnw.cpib.project.km.token.symbols.MinusOperator;
import ch.fhnw.cpib.project.km.token.symbols.PlusOperator;
import ch.fhnw.cpib.project.km.token.various.Operator;
import ch.fhnw.cpib.project.km.vm.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.project.km.vm.IInstructions;

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
	public void checkScope(Environment env) throws ScopeCheckingException {
		expression.checkScope(env);
	}

	@Override
	public Type checkType(Environment env) throws TypeCheckingException {
		Type type = expression.checkType(env);
		if (operator instanceof AddOperator) {
			if (!(type instanceof IntegerType)) {
				throw new TypeCheckingException(
						"Cannot use minus/plus operators on a Bool. Expression is " + expression.toString(""));
			}
			return type;
		}
		if (operator instanceof NotOperator) {
			if (!(type instanceof IntegerType)) {
				throw new TypeCheckingException(
						"Cannot use not operator on a Integer. Expression is " + expression.toString(""));
			}
			return type;
		}
		throw new RuntimeException("Missing type for operator " + operator + " in MonadicExpr.checkType");
	}

	@Override
	public void checkInit(Environment env) throws InitCheckingException {
		// Todo
	}

	@Override
	public void createCode(CodeGenerationEnvironment cgenv) throws CodeTooSmallError, CodeGenerationException {
		Type type = expression.getTypeSafe(cgenv.env);
		if (operator instanceof PlusOperator) {
			// ignore Plus operator
		} else if (operator instanceof MinusOperator && type instanceof Int32) {
			// int32
			expression.createCode(cgenv);
			cgenv.code.put(cgenv.locInc(), new IInstructions.NegInt());
		}  else if (operator instanceof MinusOperator && type instanceof Int64) {
			// int64
			expression.createCode(cgenv);
			cgenv.code.put(cgenv.locInc(), new IInstructions.NegInt64());
		} else if (operator instanceof NotOperator) {
			// boolean not
			expression.createCode(cgenv);
			cgenv.code.put(cgenv.locInc(), new IInstructions.InvBool());
		}
		throw new CodeGenerationException("Missing operator " + operator + " in MonadicExpr.createCode");
	}

}
