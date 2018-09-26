package ch.fhnw.cpib.project.km.token.various;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class Int64Literal extends Literal {

	private final long value;
	
	public Int64Literal(long value) {
		// TODO fix
		super("");
		this.value = value;
	}

	public long getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "Int64 " + value + "), ";
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		// TODO check if singed/unsigned
		return new Int64Literal(Long.parseLong(s));
	}
}
