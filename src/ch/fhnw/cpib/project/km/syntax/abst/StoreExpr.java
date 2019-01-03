package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.exceptions.ConstCheckingException;
import ch.fhnw.cpib.project.km.exceptions.InitCheckingException;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingException;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingException;
import ch.fhnw.cpib.project.km.token.keywords.Const;
import ch.fhnw.cpib.project.km.token.keywords.Type;
import ch.fhnw.cpib.project.km.token.various.Identifier;

public class StoreExpr implements IExpression {
	private final FullIdentifier identifier;
	private final boolean isInitialization;

	public StoreExpr(Identifier identifier, boolean isInitialization) {
		this.identifier = new FullIdentifier(null, null, null, identifier, null);
		this.isInitialization = isInitialization;
	}

	@Override
	public String toString(String indent) {
		return indent + "(" + this.getClass().getSimpleName() + ", " + identifier.toString("") + ", isInitialization="
				+ isInitialization + ")\n";
	}

	@Override
	public void addToEnvironment(Environment env, Context context) {
		env.contextMapping.put(this, context);
	}

	@Override
	public void checkScope(Environment env) throws ScopeCheckingException {
		Context context = env.contextMapping.get(this);
		if (!context.symbolTable.contains(identifier)) {
			throw new ScopeCheckingException(
					"identifier " + identifier.getIdentifierName() + " does not exist in current scope");
		}
	}

	@Override
	public boolean isLValue() {
		return true;
	}

	@Override
	public boolean isConst(Environment env) {
		if (isInitialization) {
			// constants can once be set with init
			// see example in Slides_IML page 35
			// multiple inits should be detected in checkInit
			return false;
		}

		// check if declaration is not const
		Context c = env.contextMapping.get(this);
		FullIdentifier declaration = c.symbolTable.getDeclaration(identifier);
		return declaration.isConst();
	}

	@Override
	public Type checkType(Environment env) throws TypeCheckingException {
		Context c = env.contextMapping.get(this);
		FullIdentifier declaration = c.symbolTable.getDeclaration(identifier);
		return declaration.getType();
	}

	@Override
	public void checkInit(Environment env) throws InitCheckingException {
		// To-Do...
		// check if store is initialized before it is read or assigned to
		// check if only initializations of uninitialized stores
		if (!isInitialization) {
			throw new InitCheckingException(identifier + " is not initialized");
		}
	}
}
