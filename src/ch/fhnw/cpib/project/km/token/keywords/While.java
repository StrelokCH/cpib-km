package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class While extends BaseToken {

	public While() {
		super("while");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new While();
	}
}
