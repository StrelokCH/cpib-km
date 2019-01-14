package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;
import ch.fhnw.cpib.project.km.token.symbols.MultiplicationOperator;

public class DivisionOperator extends MultiplicationOperator {

	public DivisionOperator() {
		super("divE");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new DivisionOperator();
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "DIV_E), ";
	}
}
