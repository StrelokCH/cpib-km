package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public class EndProcedure extends Base {

	public EndProcedure() {
		super("endproc");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new EndProcedure();
	}
}
