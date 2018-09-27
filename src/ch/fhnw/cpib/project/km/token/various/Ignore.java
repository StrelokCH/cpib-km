package ch.fhnw.cpib.project.km.token.various;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class Ignore extends BaseToken {

	public Ignore() {
		super("[\\n\\r\\x20]+");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		// no Token will be created as spaces and newlines are ignored
		return null;
	}
}
