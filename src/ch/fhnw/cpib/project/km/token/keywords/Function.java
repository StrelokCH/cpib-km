package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public class Function extends Base {

	public Function() {
		super("fun");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new Function();
	}
}
