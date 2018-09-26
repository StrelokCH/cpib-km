package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public abstract class Mechmode extends Base {

	public Mechmode(String regex) {
		super(regex);
	}

	@Override
	public String toString() {
		return "MECHMODE, ";
	}
}
