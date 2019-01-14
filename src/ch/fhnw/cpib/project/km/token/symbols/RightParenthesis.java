package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class RightParenthesis extends BaseToken {

	public RightParenthesis() {
		super("\\)");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new RightParenthesis();
	}

}
