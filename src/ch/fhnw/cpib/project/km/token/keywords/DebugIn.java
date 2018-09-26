package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public class DebugIn extends Base {

	public DebugIn() {
		super("debugin");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new DebugIn();
	}
}
