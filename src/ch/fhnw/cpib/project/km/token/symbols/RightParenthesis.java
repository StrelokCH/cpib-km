package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.Base;

public class RightParenthesis extends Base {

	public RightParenthesis() {
		super("\\)");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new RightParenthesis();
	}

}
