package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.exceptions.AliasingCheckingException;
import ch.fhnw.cpib.project.km.exceptions.ConstCheckingException;
import ch.fhnw.cpib.project.km.exceptions.InitCheckingException;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingException;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingException;
import ch.fhnw.cpib.project.km.token.keywords.Bool;

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
}
