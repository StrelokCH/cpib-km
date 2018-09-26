package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class LowerThanOperator extends RelationalOperator {

	public LowerThanOperator() {
		super("<");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new LowerThanOperator();
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "LT), ";
	}
}
