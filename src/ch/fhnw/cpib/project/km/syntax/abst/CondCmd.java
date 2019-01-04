package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.exceptions.AliasingCheckingException;
import ch.fhnw.cpib.project.km.exceptions.ConstCheckingException;
import ch.fhnw.cpib.project.km.exceptions.InitCheckingException;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingException;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingException;
import ch.fhnw.cpib.project.km.token.keywords.Bool;

public class CondCmd implements ICommand {

	private IExpression expression;
	private ICommand ifCase;
	private ICommand elseCase;
		
	public CondCmd(IExpression expression, ICommand ifCase, ICommand elseCase) {
		super();
		this.expression = expression;
		this.ifCase = ifCase;
		this.elseCase = elseCase;
	}

	@Override
	public String toString(String indent) {
		return indent + "(" + this.getClass().getSimpleName() + ")\n"
				+ expression.toString(indent + "    \n")
				+ ifCase.toString(indent + "    \n")
				+ elseCase.toString(indent + "    \n") ;
	}

	@Override
	public void addToEnvironment(Environment env, Context context) {
		env.contextMapping.put(this, context);
		expression.addToEnvironment(env,context);
		ifCase.addToEnvironment(env, context);
		elseCase.addToEnvironment(env, context);
	}

	@Override
	public void checkScope(Environment env) throws ScopeCheckingException {
		expression.checkScope(env);
		ifCase.checkScope(env);
		elseCase.checkScope(env);
	}

	@Override
	public void checkType(Environment env) throws TypeCheckingException {
		if (!(expression.checkType(env) instanceof Bool)){
			throw new TypeCheckingException("condition " + expression.toString("") + " must be of type Bool");
		}
		ifCase.checkType(env);
		elseCase.checkType(env);
	}

	@Override
	public void checkConst(Environment env) throws ConstCheckingException {
		ifCase.checkConst(env);
		elseCase.checkConst(env);
	}

	@Override
	public void checkInit(Environment env) throws InitCheckingException {
		expression.checkInit(env);
		ifCase.checkInit(env);
		elseCase.checkInit(env);
	}

	@Override
	public void checkAliasing(Environment env) throws AliasingCheckingException {
		// not needed
	}
}
