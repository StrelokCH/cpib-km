package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class LowerEqualsOperator extends RelationalOperator {

	public LowerEqualsOperator() {
		super("<=");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new LowerEqualsOperator();
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "LE), ";
	}
}
