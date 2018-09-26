package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public class Then extends Base {

	public Then() {
		super("then");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new Then();
	}
}
