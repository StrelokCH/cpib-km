package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.BaseToken;

public abstract class MultiplicationOperator extends BaseToken {

	public MultiplicationOperator(String regex) {
		super(regex);
	}

	@Override
	public String toString() {
		return "MULTOPR, ";
	}
}
