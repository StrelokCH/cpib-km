package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.Base;

public class PlusOperator extends AddOperator {

	public PlusOperator() {
		super("\\+");
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "PLUS), ";
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new PlusOperator();
	}
}
