package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.analysis.TypePromoter;
import ch.fhnw.cpib.project.km.exceptions.AliasingCheckingException;
import ch.fhnw.cpib.project.km.exceptions.CodeGenerationException;
import ch.fhnw.cpib.project.km.exceptions.ConstCheckingException;
import ch.fhnw.cpib.project.km.exceptions.InitCheckingException;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingException;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingException;
import ch.fhnw.cpib.project.km.synthesis.CodeGenerationEnvironment;
import ch.fhnw.cpib.project.km.token.keywords.Type;
import ch.fhnw.cpib.project.km.vm.IInstructions;
import ch.fhnw.cpib.project.km.vm.ICodeArray.CodeTooSmallError;

public class AssiCmd implements ICommand {

	// left
	private final IExpression expression1;
	// right
	private final IExpression expression2;

	public AssiCmd(IExpression expression1, IExpression expression2) {
		super();
		this.expression1 = expression1;
		this.expression2 = expression2;
	}

	@Override
	public String toString(String indent) {
		return indent + "(" + this.getClass().getSimpleName() + ")\n" + expression1.toString(indent + "    \n")
				+ expression2.toString(indent + "    \n");
	}

	@Override
	public void addToEnvironment(Environment env, Context context) {
		env.contextMapping.put(this, context);
		expression1.addToEnvironment(env, context);
		expression2.addToEnvironment(env, context);
	}

	@Override
	public void checkScope(Environment env) throws ScopeCheckingException {
		expression1.checkScope(env);
		expression2.checkScope(env);

		if (!expression1.isLValue()) {
			throw new ScopeCheckingException("expression " + expression1.toString("") + "should be an L-Value");
		}
	}

	@Override
	public void checkType(Environment env) throws TypeCheckingException {
		Type type1 = expression1.checkType(env);
		Type type2 = expression2.checkType(env);
		if (!TypePromoter.canPromote(type2, type1)) {
			throw new TypeCheckingException("can't assign " + expression2.toString("") + " with type="
					+ type2.toString() + " to " + expression1.toString("") + " with type=" + type1);
		}
	}

	@Override
	public void checkConst(Environment env) throws ConstCheckingException {
		if (expression1.isConst(env)) {
			throw new ConstCheckingException("identifier " + expression1.toString("") + " is const");
		}
	}

	@Override
	public void checkInit(Environment env) throws InitCheckingException {
		expression2.checkInit(env);
	}

	@Override
	public void checkAliasing(Environment env) throws AliasingCheckingException {
		// not needed
	}

	@Override
	public void createCode(CodeGenerationEnvironment cgenv) throws CodeTooSmallError, CodeGenerationException {
		// load right side to stack
		expression2.createCode(cgenv);

		// load target address to stack
		expression1.createCodeLoadAddr(cgenv);

		// store input to address
		cgenv.code.put(cgenv.locInc(), new IInstructions.Store());
	}
}
