package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class If extends BaseToken {

	public If() {
		super("if");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new If();
	}
}
