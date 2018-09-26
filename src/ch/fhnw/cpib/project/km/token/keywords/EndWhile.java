package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public class EndWhile extends Base {

	public EndWhile() {
		super("endwhile");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new EndWhile();
	}
}
