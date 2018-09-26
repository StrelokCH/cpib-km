package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;

public abstract class Type extends BaseToken {

	public Type(String regex) {
		super(regex);
	}

	@Override
	public String toString() {
		return "TYPE, ";
	}
}
