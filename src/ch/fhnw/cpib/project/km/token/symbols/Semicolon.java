package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class Semicolon extends BaseToken {

	public Semicolon() {
		super(";");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new Semicolon();
	}
}
