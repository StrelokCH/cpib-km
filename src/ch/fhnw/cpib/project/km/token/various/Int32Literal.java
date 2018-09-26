package ch.fhnw.cpib.project.km.token.various;

import ch.fhnw.cpib.project.km.token.Base;

public class Int32Literal extends Literal {

	private final int value;
	
	public Int32Literal(int value) {
		// TODO fix
		// Question: is int32 unsigned?
		super("");
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "Int32 " + value + "), ";
	}

	@Override
	protected Base internalCreateToken(String s) {
		// TODO check if signed/unsigned
		return new Int32Literal(Integer.parseInt(s));
	}
}
