package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class NotEqualsOperator extends RelationalOperator {

	public NotEqualsOperator() {
		super("/=");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new NotEqualsOperator();
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "NE), ";
	}
}
