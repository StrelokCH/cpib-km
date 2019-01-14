package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class Do extends BaseToken {

	public Do() {
		super("do");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new Do();
	}
}
