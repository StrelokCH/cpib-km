package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public abstract class Type extends Base {

	public Type(String regex) {
		super(regex);
	}

	@Override
	public String toString() {
		return "TYPE, ";
	}
}
