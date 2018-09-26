package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class EndWhile extends BaseToken {

	public EndWhile() {
		super("endwhile");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new EndWhile();
	}
}
