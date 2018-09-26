package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public class Init extends Base {

	public Init() {
		super("init");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new Init();
	}
}
