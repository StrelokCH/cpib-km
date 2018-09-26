package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class DebugIn extends BaseToken {

	public DebugIn() {
		super("debugin");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new DebugIn();
	}
}
