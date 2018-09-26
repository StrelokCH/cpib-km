package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.Base;

public class GreaterEqualsOperator extends RelationalOperator {

	public GreaterEqualsOperator() {
		super(">=");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new GreaterEqualsOperator();
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "GE), ";
	}
}
