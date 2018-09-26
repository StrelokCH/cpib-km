package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public class Returns extends Base {

	public Returns() {
		super("returns");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new Returns();
	}
}
