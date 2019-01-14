package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class Call extends BaseToken {

	public Call() {
		super("call");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new Call();
	}
}
