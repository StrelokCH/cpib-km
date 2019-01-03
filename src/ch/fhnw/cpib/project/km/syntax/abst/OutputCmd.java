package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.exceptions.ConstCheckingException;
import ch.fhnw.cpib.project.km.exceptions.InitCheckingException;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingException;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingException;

public class OutputCmd implements ICommand {

	private IExpression expression;
		
	public OutputCmd(IExpression expression) {
		super();
		this.expression = expression;
	}

	@Override
	public String toString(String indent) {
		return indent + "(" + this.getClass().getSimpleName() + ")\n"
				+ expression.toString(indent + "    \n");
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
		// Not needed?
	}

	@Override
	public void checkInit(Environment env) throws InitCheckingException {
		expression.checkInit(env);
	}
}
