package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public class Int64 extends Type {

	public Int64() {
		super("int64");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new Int64();
	}
}
