package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.Base;

public abstract class AddOperator extends Base {

	public AddOperator(String regex) {
		super(regex);
	}

	@Override
	public String toString() {
		return "ADDOPR, ";
	}
}
