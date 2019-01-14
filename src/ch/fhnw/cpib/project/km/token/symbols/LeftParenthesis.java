package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class LeftParenthesis extends BaseToken {

	public LeftParenthesis() {
		super("\\(");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new LeftParenthesis();
	}
}
