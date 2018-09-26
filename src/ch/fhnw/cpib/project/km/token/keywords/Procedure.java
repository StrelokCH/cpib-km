package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class Procedure extends BaseToken {

	public Procedure() {
		super("proc");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new Procedure();
	}
}
