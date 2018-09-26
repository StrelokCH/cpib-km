package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.Base;

public class Colon extends Base {

	public Colon() {
		super(":");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new Colon();
	}
}
