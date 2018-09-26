package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public class Skip extends Base {

	public Skip() {
		super("skip");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new Skip();
	}
}
