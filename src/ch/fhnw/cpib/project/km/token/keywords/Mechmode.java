package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;

public abstract class Mechmode extends BaseToken {

	public Mechmode(String regex) {
		super(regex);
	}

	@Override
	public String toString() {
		return "MECHMODE, ";
	}
}
