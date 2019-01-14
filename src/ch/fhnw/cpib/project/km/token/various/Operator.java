package ch.fhnw.cpib.project.km.token.various;

import ch.fhnw.cpib.project.km.token.BaseToken;

public abstract class Operator extends BaseToken {

	public Operator(String regex) {
		super(regex);
	}

	@Override
	public String toString() {
		return "OPERATOR, ";
	}
}
