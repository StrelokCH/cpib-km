package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class Init extends BaseToken {

	public Init() {
		super("init");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new Init();
	}
}
