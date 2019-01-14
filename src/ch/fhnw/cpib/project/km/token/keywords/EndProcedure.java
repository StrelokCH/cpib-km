package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class EndProcedure extends BaseToken {

	public EndProcedure() {
		super("endproc");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new EndProcedure();
	}
}
