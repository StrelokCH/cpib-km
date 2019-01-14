package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class Function extends BaseToken {

	public Function() {
		super("fun");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new Function();
	}
}
