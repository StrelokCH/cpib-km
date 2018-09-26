package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;

public abstract class ChangeMode extends BaseToken {

	public ChangeMode(String regex) {
		super(regex);
	}

	@Override
	public String toString() {
		return "CHANGEMODE, ";
	}
}
