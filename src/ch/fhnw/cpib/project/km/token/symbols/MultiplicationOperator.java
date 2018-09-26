package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.Base;

public abstract class MultiplicationOperator extends Base {

	public MultiplicationOperator(String regex) {
		super(regex);
	}

	@Override
	public String toString() {
		return "MULTOPR, ";
	}
}
