package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.Base;

public class EqualsOperator extends RelationalOperator {

	public EqualsOperator() {
		super("=");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new EqualsOperator();
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "EQ), ";
	}
}
