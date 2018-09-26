package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.Base;

public class COrOperator extends BooleanOperator {

	public COrOperator() {
		super("\\|\\?");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new COrOperator();
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "COR), ";
	}
}
