package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.various.Operator;

public abstract class BooleanOperator extends Operator {

	public BooleanOperator(String regex) {
		super(regex);
	}

	@Override
	public String toString() {
		return "BOOLOPR, ";
	}
}
