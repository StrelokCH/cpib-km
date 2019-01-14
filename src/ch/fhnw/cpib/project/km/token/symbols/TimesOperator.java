package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class TimesOperator extends MultiplicationOperator {

	public TimesOperator() {
		super("\\*");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new TimesOperator();
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "TIMES), ";
	}
}
