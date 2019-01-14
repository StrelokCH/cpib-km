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
import ch.fhnw.cpib.project.km.vm.IInstructions;
import ch.fhnw.cpib.project.km.vm.ICodeArray.CodeTooSmallError;

public class WhileCmd implements ICommand {

	private IExpression expression;
	private ICommand command;

	public WhileCmd(IExpression expression, ICommand command) {
		super();
		this.expression = expression;
		this.command = command;
	}

	@Override
	public String toString(String indent) {
		return indent + "(" + this.getClass().getSimpleName() + ")\n" + expression.toString(indent + "    \n")
				+ command.toString(indent + "    \n");
	}

	@Override
	public void addToEnvironment(Environment env, Context context) {
		env.contextMapping.put(this, context);
		expression.addToEnvironment(env, context);
		command.addToEnvironment(env, context);
	}

	@Override
	public void checkScope(Environment env) throws ScopeCheckingException {
		expression.checkScope(env);
		command.checkScope(env);
	}

	@Override
	public void checkType(Environment env) throws TypeCheckingException {
		if (!(expression.checkType(env) instanceof Bool)){
			throw new TypeCheckingException("condition " + expression.toString("") + " must be of type Bool");
		}
		command.checkType(env);
	}

	@Override
	public void checkConst(Environment env) throws ConstCheckingException {
		command.checkConst(env);
	}

	@Override
	public void checkInit(Environment env) throws InitCheckingException {
		expression.checkInit(env);
		command.checkInit(env);
	}

	@Override
	public void checkAliasing(Environment env) throws AliasingCheckingException {
		// not needed
	}

	@Override
	public void createCode(CodeGenerationEnvironment cgenv) throws CodeTooSmallError, CodeGenerationException {
		int loc = cgenv.loc();
		
		// load value of expression to stack
		expression.createCode(cgenv);
		
		int loc1 = cgenv.locInc();
		
		command.createCode(cgenv);
		
		int loc3 = cgenv.locInc();
		int loc4 = cgenv.loc();
		
		cgenv.code.put(loc1, new IInstructions.CondJump(loc4));
		cgenv.code.put(loc3, new IInstructions.UncondJump(loc));
	}
}
