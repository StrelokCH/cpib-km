package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.Base;

public class CAndOperator extends BooleanOperator {

	public CAndOperator() {
		super("\\&\\?");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new CAndOperator();
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "CAND), ";
	}
}
