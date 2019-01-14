package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class MinusOperator extends AddOperator {

	public MinusOperator() {
		super("\\-");
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "MINUS), ";
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new MinusOperator();
	}
}
