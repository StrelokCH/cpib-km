package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public class Do extends Base {

	public Do() {
		super("do");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new Do();
	}
}
