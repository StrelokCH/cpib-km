package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public class If extends Base {

	public If() {
		super("if");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new If();
	}
}
