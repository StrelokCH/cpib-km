package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class Global extends BaseToken {

	public Global() {
		super("global");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new Global();
	}
}
