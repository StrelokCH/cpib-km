package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public class DebugOut extends Base {

	public DebugOut() {
		super("debugout");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new DebugOut();
	}
}
