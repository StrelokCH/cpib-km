package ch.fhnw.cpib.project.km.token.various;

import ch.fhnw.cpib.project.km.token.BaseToken;

public abstract class Literal extends BaseToken {

	public Literal(String regex) {
		super(regex);
	}

	@Override
	public String toString() {
		return "LITERAL, ";
	}
}
