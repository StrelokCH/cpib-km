package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public class Procedure extends Base {

	public Procedure() {
		super("proc");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new Procedure();
	}
}
