package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.Base;

public class Semicolon extends Base {

	public Semicolon() {
		super(";");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new Semicolon();
	}
}
