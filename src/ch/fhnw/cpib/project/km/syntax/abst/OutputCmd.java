package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.exceptions.AliasingCheckingException;
import ch.fhnw.cpib.project.km.exceptions.CodeGenerationException;
import ch.fhnw.cpib.project.km.exceptions.ConstCheckingException;
import ch.fhnw.cpib.project.km.exceptions.InitCheckingException;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingException;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingException;
import ch.fhnw.cpib.project.km.synthesis.CodeGenerationEnvironment;
import ch.fhnw.cpib.project.km.token.keywords.Bool;
import ch.fhnw.cpib.project.km.token.keywords.Int32;
import ch.fhnw.cpib.project.km.token.keywords.Int64;
import ch.fhnw.cpib.project.km.token.keywords.Type;
import ch.fhnw.cpib.project.km.vm.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.project.km.vm.IInstructions;

public class OutputCmd implements ICommand {

	private IExpression expression;

	public OutputCmd(IExpression expression) {
		super();
		this.expression = expression;
	}

	@Override
	public String toString(String indent) {
		return indent + "(" + this.getClass().getSimpleName() + ")\n" + expression.toString(indent + "    \n");
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
	public void checkType(Environment env) throws TypeCheckingException {
		expression.checkType(env);
	}

	@Override
	public void checkConst(Environment env) throws ConstCheckingException {
		// not needed
	}

	@Override
	public void checkInit(Environment env) throws InitCheckingException {
		expression.checkInit(env);
	}

	@Override
	public void checkAliasing(Environment env) throws AliasingCheckingException {
		// not needed
	}

	@Override
	public void createCode(CodeGenerationEnvironment cgenv) throws CodeTooSmallError, CodeGenerationException {
		// load expression
		expression.createCode(cgenv);

		// output
		Type type = expression.getTypeSafe(cgenv.env);
		String indicator = expression.toString("");
		createCode(cgenv, type, indicator);
	}

	/**
	 * Output top of stack with specified type.
	 * 
	 * @param cgenv
	 * @param type
	 * @param identifierName
	 * @throws CodeGenerationException
	 * @throws CodeTooSmallError
	 */
	public static void createCode(CodeGenerationEnvironment cgenv, Type type, String indicator)
			throws CodeGenerationException, CodeTooSmallError {
		IInstructions.IInstr instruction = null;
		if (type instanceof Int32) {
			instruction = new IInstructions.OutputInt(indicator);
		} else if (type instanceof Int64) {
			instruction = new IInstructions.OutputInt64(indicator);
		} else if (type instanceof Bool) {
			instruction = new IInstructions.OutputBool(indicator);
		} else {
			throw new CodeGenerationException("invalid type in OutputCmd.createCode. Type is " + type.toString() + ".");
		}
		cgenv.code.put(cgenv.locInc(), instruction);
	}
}
