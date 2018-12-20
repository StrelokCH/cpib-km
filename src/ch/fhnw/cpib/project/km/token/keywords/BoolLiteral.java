package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;
import ch.fhnw.cpib.project.km.token.various.Literal;

public class BoolLiteral extends Literal {
	
	private final boolean value;
	
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
	public boolean equals(Object obj) {
		return super.equals(obj) && ((BoolLiteral)obj).value == this.value;
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new BoolLiteral(Boolean.parseBoolean(s));
	}
}
