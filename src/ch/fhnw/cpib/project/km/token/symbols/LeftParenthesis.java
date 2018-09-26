package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.Base;

public class LeftParenthesis extends Base {

	public LeftParenthesis() {
		super("\\(");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new LeftParenthesis();
	}
}
