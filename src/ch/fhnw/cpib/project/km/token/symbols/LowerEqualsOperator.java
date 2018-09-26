package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.Base;

public class LowerEqualsOperator extends RelationalOperator {

	public LowerEqualsOperator() {
		super("<=");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new LowerEqualsOperator();
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "LE), ";
	}
}
