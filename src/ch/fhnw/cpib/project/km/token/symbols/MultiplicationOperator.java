package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.various.Operator;

public abstract class MultiplicationOperator extends Operator {

	public MultiplicationOperator(String regex) {
		super(regex);
	}

	@Override
	public String toString() {
		return "MULTOPR, ";
	}
}
