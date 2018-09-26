package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class EndProgram extends BaseToken {

	public EndProgram() {
		super("endproc");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new EndProgram();
	}
}
