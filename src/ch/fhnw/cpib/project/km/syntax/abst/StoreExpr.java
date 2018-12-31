package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.exceptions.ConstCheckingError;
import ch.fhnw.cpib.project.km.exceptions.InitCheckingError;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingError;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingError;
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
	
	@Override
	public Type checkType(Environment env) throws TypeCheckingError {
		Type type = identifier.getType();
		if (type == null) {
			Context c = env.contextMapping.get(this);
			type = c.symbolTable.getTypeForLocalVariable(identifier.getIdentifierName());
			this.identifier.setType(type);
		}
		return type;
	}

	@Override
	public void checkConst(Environment env) throws ConstCheckingError {
		if(identifier.changemode instanceof Const) {
			throw new ConstCheckingError("identifier " + identifier.getIdentifierName() + " is const");
		}
	}

	@Override
	public void checkInit(Environment env) throws InitCheckingError {
		//To-Do...
		// check if store is initialized before it is read or assigned to
		//check if only initializations of uninitialized stores
		if(!isInitialization) {
			throw new InitCheckingError(identifier + " is not initialized");
		}
	}
}
