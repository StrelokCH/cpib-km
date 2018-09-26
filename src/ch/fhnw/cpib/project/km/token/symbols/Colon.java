package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class Colon extends BaseToken {

	public Colon() {
		super(":");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new Colon();
	}
}
