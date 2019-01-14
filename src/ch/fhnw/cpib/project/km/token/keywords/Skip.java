package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class Skip extends BaseToken {

	public Skip() {
		super("skip");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new Skip();
	}
}
