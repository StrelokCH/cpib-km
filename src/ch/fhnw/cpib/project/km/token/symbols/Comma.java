package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class Comma extends BaseToken {

	public Comma() {
		super(",");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new Comma();
	}
}
