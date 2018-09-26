package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class NotOperator extends BaseToken {

	public NotOperator() {
		super("not");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new NotOperator();
	}
}
