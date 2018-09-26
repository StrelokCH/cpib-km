package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public class EndIf extends Base {

	public EndIf() {
		super("endif");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new EndIf();
	}
}
