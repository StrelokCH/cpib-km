package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class EndIf extends BaseToken {

	public EndIf() {
		super("endif");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new EndIf();
	}
}
