package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Literal;

public class BoolLiteral extends Literal {
	
	final boolean value;
	
	public BoolLiteral(boolean value) {
		super();
		this.value = value;
	}

	public boolean getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "BoolVal " + value + "), ";
	}
}
