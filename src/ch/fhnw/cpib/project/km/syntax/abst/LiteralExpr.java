package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.exceptions.CodeGenerationException;
import ch.fhnw.cpib.project.km.exceptions.ConstCheckingException;
import ch.fhnw.cpib.project.km.exceptions.InitCheckingException;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingException;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingException;
import ch.fhnw.cpib.project.km.synthesis.CodeGenerationEnvironment;
import ch.fhnw.cpib.project.km.token.keywords.Bool;
import ch.fhnw.cpib.project.km.token.keywords.BoolLiteral;
import ch.fhnw.cpib.project.km.token.keywords.Int32;
import ch.fhnw.cpib.project.km.token.keywords.Int64;
import ch.fhnw.cpib.project.km.token.keywords.Type;
import ch.fhnw.cpib.project.km.token.various.Int32Literal;
import ch.fhnw.cpib.project.km.token.various.Int64Literal;
import ch.fhnw.cpib.project.km.token.various.Literal;
import ch.fhnw.cpib.project.km.vm.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.project.km.vm.IInstructions;

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
	public void checkScope(Environment env) throws ScopeCheckingException {
		// not needed
	}

	@Override
	public Type checkType(Environment env) throws TypeCheckingException {
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
	public void checkInit(Environment env) throws InitCheckingException {
		// not needed
	}

	@Override
	public void createCode(CodeGenerationEnvironment cgenv) throws CodeTooSmallError, CodeGenerationException {
		IInstructions.IInstr instruction = null;
		if (literal instanceof Int32Literal) {
			instruction = new IInstructions.LoadImInt(((Int32Literal)literal).getValue());
		} else if (literal instanceof Int64Literal) {
			instruction = new IInstructions.LoadImInt64(((Int64Literal)literal).getValue());
		} else if (literal instanceof BoolLiteral) {
			instruction = new IInstructions.LoadImInt(((BoolLiteral)literal).getValue() ? 1 : 0);
		} else {
			throw new CodeGenerationException("Missing code for literal " + literal + " in LiteralExpr.createCode");
		}
		cgenv.code.put(cgenv.locInc(), instruction);
	}

}
