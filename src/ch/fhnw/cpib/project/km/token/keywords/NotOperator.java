package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public class NotOperator extends Base {

	public NotOperator() {
		super("not");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new NotOperator();
	}
}
