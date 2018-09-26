package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.symbols.MultiplicationOperator;

public class DivisionOperator extends MultiplicationOperator {

	@Override
	public String toString() {
		return "(" + super.toString() + "DIV_E), ";
	}
}
