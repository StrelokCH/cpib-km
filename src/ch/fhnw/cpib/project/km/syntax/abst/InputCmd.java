package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.exceptions.ConstCheckingError;
import ch.fhnw.cpib.project.km.exceptions.InitCheckingError;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingError;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingError;

public class InputCmd implements ICommand {

	private IExpression expression;
	
	
	public InputCmd(IExpression expression) {
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
		expression.addToEnvironment(env,context);
	}

	@Override
	public void checkScope(Environment env) throws ScopeCheckingError {
		expression.checkScope(env);
		
		if (!expression.isLValue()) {
			throw new ScopeCheckingError("expression " + expression.toString("") + "should be an L-Value");
		}
	}

	@Override
	public void checkType(Environment env) throws TypeCheckingError {
		// not necessary as expression must be a L-Value
	}

	@Override
	public void checkConst(Environment env) throws ConstCheckingError {
		// Not needed?
		
	}

	@Override
	public void checkInit(Environment env) throws InitCheckingError {
		expression.checkInit(env);
	}
}
