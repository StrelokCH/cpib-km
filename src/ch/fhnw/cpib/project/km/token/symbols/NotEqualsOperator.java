package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.Base;

public class NotEqualsOperator extends RelationalOperator {

	public NotEqualsOperator() {
		super("/=");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new NotEqualsOperator();
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "NE), ";
	}
}
