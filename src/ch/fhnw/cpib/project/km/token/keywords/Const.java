package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class Const extends Changemode {

	public Const() {
		super("const");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new Const();
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "CONST), ";
	}
}
