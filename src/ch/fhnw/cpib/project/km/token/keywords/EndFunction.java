package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public class EndFunction extends Base {

	public EndFunction() {
		super("endfun");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new EndFunction();
	}
}
