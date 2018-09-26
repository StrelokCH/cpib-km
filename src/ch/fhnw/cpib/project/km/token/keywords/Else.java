package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class Else extends BaseToken {

	public Else() {
		super("else");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new Else();
	}
}
