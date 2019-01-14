package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.various.Operator;

public abstract class AddOperator extends Operator {

	public AddOperator(String regex) {
		super(regex);
	}

	@Override
	public String toString() {
		return "ADDOPR, ";
	}
}
