package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public class Call extends Base {

	public Call() {
		super("call");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new Call();
	}
}
