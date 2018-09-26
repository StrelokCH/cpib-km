package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;
import ch.fhnw.cpib.project.km.token.various.Literal;

public class BoolLiteral extends Literal {
	
	final boolean value;
	
	public BoolLiteral(boolean value) {
		super("(true)|(false)");
		this.value = value;
	}

	public boolean getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "BoolVal " + value + "), ";
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		// TODO Auto-generated method stub
		return new BoolLiteral(Boolean.parseBoolean(s));
	}
}
