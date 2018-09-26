package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public class Else extends Base {

	public Else() {
		super("else");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new Else();
	}
}
