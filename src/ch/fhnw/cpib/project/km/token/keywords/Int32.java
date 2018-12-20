package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class Int32 extends IntegerType {

	public Int32() {
		super("int32");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new Int32();
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "Int32)";
	}
}
