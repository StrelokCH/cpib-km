package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class Local extends BaseToken {

	public Local() {
		super("local");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new Local();
	}
}
