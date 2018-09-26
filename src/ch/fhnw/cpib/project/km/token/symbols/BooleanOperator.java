package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.Base;

public abstract class BooleanOperator extends Base {

	public BooleanOperator(String regex) {
		super(regex);
	}

	@Override
	public String toString() {
		return "BOOLOPR, ";
	}
}
