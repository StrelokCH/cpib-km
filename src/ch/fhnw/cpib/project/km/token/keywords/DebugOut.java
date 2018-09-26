package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class DebugOut extends BaseToken {

	public DebugOut() {
		super("debugout");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new DebugOut();
	}
}
