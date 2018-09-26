package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public class EndProgram extends Base {

	public EndProgram() {
		super("endproc");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new EndProgram();
	}
}
