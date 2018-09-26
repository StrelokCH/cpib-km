package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public abstract class ChangeMode extends Base {

	public ChangeMode(String regex) {
		super(regex);
	}

	@Override
	public String toString() {
		return "CHANGEMODE, ";
	}
}
