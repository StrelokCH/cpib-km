package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingError;
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
		return indent + "(" + this.getClass().getSimpleName() + ", " + identifier.toString() + ", isInitialization="
				+ isInitialization + ")\n";
	}

	@Override
	public void addToEnvironment(Environment env, Context context) {
		env.contextMapping.put(this, context);
	}

	@Override
	public void checkScope(Environment env) throws ScopeCheckingError {
		Context context = env.contextMapping.get(this);
		if (!context.symbolTable.contains(identifier)) {
			throw new ScopeCheckingError("identifier " + identifier.getIdentifierName() + " does not exist in current scope");
		}
	}

	@Override
	public boolean isLValue() {
		return true;
	}
}
