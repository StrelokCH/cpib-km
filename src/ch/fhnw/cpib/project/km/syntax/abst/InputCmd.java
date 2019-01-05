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
import ch.fhnw.cpib.project.km.vm.IInstructions.IExecInstr;
import ch.fhnw.cpib.project.km.vm.IInstructions;
import ch.fhnw.cpib.project.km.vm.VirtualMachine;

public class InputCmd implements ICommand {

	private IExpression expression;

	public InputCmd(IExpression expression) {
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

		if (!expression.isLValue()) {
			throw new ScopeCheckingException("expression " + expression.toString("") + "should be an L-Value");
		}
		if (expression.isConst(env)) {
			throw new ScopeCheckingException("expression " + expression.toString("") + " must not be const");
		}
	}

	@Override
	public void checkType(Environment env) throws TypeCheckingException {
		// not necessary as expression must be a L-Value
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

	public static void createCode(CodeGenerationEnvironment cgenv, Type type, String indicator)
			throws CodeTooSmallError, CodeGenerationException {
		IInstructions.IInstr instruction = null;
		if (type instanceof Int32) {
			instruction = new IInstructions.InputInt(indicator);
		} else if (type instanceof Int64) {
			instruction = new IInstructions.InputInt64(indicator);
		} else if (type instanceof Bool) {
			instruction = new IInstructions.InputBool(indicator);
		} else {
			throw new CodeGenerationException("invalid type in InputCmd.createCode. Type is " + type.toString() + ".");
		}
		cgenv.code.put(cgenv.locInc(), instruction);
	}

	@Override
	public void createCode(CodeGenerationEnvironment cgenv) throws CodeTooSmallError, CodeGenerationException {
		// load target address to stack
		expression.createCodeLoadAddr(cgenv);

		// query input to stack
		createCode(cgenv, expression.getTypeSafe(cgenv.env), expression.toString(""));

		// store input to address
		cgenv.code.put(cgenv.locInc(), new IInstructions.Store());
	}
}
