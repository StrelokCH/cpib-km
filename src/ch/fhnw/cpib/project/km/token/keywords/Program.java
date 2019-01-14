package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class Program extends BaseToken {

	public Program() {
		super("program");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new Program();
	}
}
