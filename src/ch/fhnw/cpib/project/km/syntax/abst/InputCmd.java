package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.exceptions.AliasingCheckingException;
import ch.fhnw.cpib.project.km.exceptions.ConstCheckingException;
import ch.fhnw.cpib.project.km.exceptions.InitCheckingException;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingException;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingException;

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
		if (!expression.isConst(env)) {
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
}
