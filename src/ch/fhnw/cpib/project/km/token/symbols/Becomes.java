package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.Base;

public class Becomes extends Base {

	public Becomes() {
		super("\\:\\=");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new Becomes();
	}
}
