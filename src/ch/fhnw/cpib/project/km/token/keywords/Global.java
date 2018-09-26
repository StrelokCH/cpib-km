package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public class Global extends Base {

	public Global() {
		super("global");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new Global();
	}
}
