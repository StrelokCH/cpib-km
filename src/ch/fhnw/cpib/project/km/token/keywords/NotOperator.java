package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;
import ch.fhnw.cpib.project.km.token.various.Operator;

public class NotOperator extends Operator {

	public NotOperator() {
		super("not");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new NotOperator();
	}
}
