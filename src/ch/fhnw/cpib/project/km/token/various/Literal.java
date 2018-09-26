package ch.fhnw.cpib.project.km.token.various;

import ch.fhnw.cpib.project.km.token.Base;

public abstract class Literal extends Base {

	public Literal(String regex) {
		super(regex);
	}

	@Override
	public String toString() {
		return "LITERAL, ";
	}
}
