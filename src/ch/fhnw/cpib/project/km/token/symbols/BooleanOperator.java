package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.BaseToken;

public abstract class BooleanOperator extends BaseToken {

	public BooleanOperator(String regex) {
		super(regex);
	}

	@Override
	public String toString() {
		return "BOOLOPR, ";
	}
}
