package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public class While extends Base {

	public While() {
		super("while");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new While();
	}
}
