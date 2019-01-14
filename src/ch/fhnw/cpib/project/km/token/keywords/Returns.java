package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class Returns extends BaseToken {

	public Returns() {
		super("returns");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new Returns();
	}
}
