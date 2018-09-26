package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public class Local extends Base {

	public Local() {
		super("local");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new Local();
	}
}
