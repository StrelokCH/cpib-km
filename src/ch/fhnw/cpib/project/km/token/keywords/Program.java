package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public class Program extends Base {

	public Program() {
		super("program");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new Program();
	}
}
