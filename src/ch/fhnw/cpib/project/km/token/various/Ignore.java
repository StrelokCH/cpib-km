package ch.fhnw.cpib.project.km.token.various;

import ch.fhnw.cpib.project.km.token.Base;

public class Ignore extends Base {

	public Ignore() {
		super("[\\n\\s]+");
	}

	@Override
	protected Base internalCreateToken(String s) {
		// no Token will be created as spaces and newlines are ignored
		return null;
	}
}
