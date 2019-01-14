package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class EndFunction extends BaseToken {

	public EndFunction() {
		super("endfun");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new EndFunction();
	}
}
