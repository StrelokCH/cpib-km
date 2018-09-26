package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class GreaterEqualsOperator extends RelationalOperator {

	public GreaterEqualsOperator() {
		super(">=");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new GreaterEqualsOperator();
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "GE), ";
	}
}
