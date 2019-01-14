package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class EqualsOperator extends RelationalOperator {

	public EqualsOperator() {
		super("=");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new EqualsOperator();
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "EQ), ";
	}
}
