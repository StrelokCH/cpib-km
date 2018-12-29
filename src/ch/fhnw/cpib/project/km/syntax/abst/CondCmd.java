package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.exceptions.ConstCheckingError;
import ch.fhnw.cpib.project.km.exceptions.InitCheckingError;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingError;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingError;
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
	public void checkScope(Environment env) throws ScopeCheckingError {
		expression.checkScope(env);
		ifCase.checkScope(env);
		elseCase.checkScope(env);
	}

	@Override
	public void checkType(Environment env) throws TypeCheckingError {
		if (!(expression.checkType(env) instanceof Bool)){
			throw new TypeCheckingError("condition " + expression.toString("") + " must be of type Bool");
		}
		ifCase.checkType(env);
		elseCase.checkType(env);
	}

	@Override
	public void checkConst(Environment env) throws ConstCheckingError {
		ifCase.checkConst(env);
		elseCase.checkConst(env);
	}

	@Override
	public void checkInit(Environment env) throws InitCheckingError {
		expression.checkInit(env);
		ifCase.checkInit(env);
		elseCase.checkInit(env);
	}
}
