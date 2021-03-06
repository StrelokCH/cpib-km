package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class Int64 extends IntegerType {

	public Int64() {
		super("int64");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new Int64();
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "Int64)";
	}
}
