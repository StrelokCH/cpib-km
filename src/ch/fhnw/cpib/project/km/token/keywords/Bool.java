package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public class Bool extends Type {

	public Bool() {
		super("bool");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new Bool();
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "BOOL)";
	}
}
