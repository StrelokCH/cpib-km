package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.BaseToken;

public abstract class AddOperator extends BaseToken {

	public AddOperator(String regex) {
		super(regex);
	}

	@Override
	public String toString() {
		return "ADDOPR, ";
	}
}
