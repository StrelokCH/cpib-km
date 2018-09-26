package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class Becomes extends BaseToken {

	public Becomes() {
		super("\\:\\=");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new Becomes();
	}
}
