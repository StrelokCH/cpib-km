package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.Base;

public class GreaterThanOperator extends RelationalOperator {

	public GreaterThanOperator() {
		super(">");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new GreaterThanOperator();
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "GT), ";
	}
}
