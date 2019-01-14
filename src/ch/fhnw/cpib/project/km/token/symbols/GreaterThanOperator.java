package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class GreaterThanOperator extends RelationalOperator {

	public GreaterThanOperator() {
		super(">");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new GreaterThanOperator();
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "GT), ";
	}
}
