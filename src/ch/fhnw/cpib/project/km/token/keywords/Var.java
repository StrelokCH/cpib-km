package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class Var extends Changemode {

	public Var() {
		super("var");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new Var();
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "VAR), ";
	}
}
