package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class Then extends BaseToken {

	public Then() {
		super("then");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new Then();
	}
}
