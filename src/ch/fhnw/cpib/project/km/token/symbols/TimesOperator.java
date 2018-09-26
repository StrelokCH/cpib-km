package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.Base;

public class TimesOperator extends MultiplicationOperator {

	public TimesOperator() {
		super("\\*");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new TimesOperator();
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "TIMES), ";
	}
}
