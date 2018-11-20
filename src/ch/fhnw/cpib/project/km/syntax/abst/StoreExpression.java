package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.token.various.Identifier;

public class StoreExpression implements IExpression {
	private final Identifier identifier;
	private final boolean isInitialization;

	public StoreExpression(Identifier identifier, boolean isInitialization) {
		this.identifier = identifier;
		this.isInitialization = isInitialization;
	}

	@Override
	public String toString(String indent) {
		return indent + "(" + this.getClass().getSimpleName() + ", " + identifier.toString() + ", isInitialization="
				+ isInitialization + ")\n";
	}
}
