package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class CAndOperator extends BooleanOperator {

	public CAndOperator() {
		super("\\&\\?");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new CAndOperator();
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "CAND), ";
	}
}
