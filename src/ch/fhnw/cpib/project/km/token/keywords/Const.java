package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public class Const extends ChangeMode {

	public Const() {
		super("const");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new Const();
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "CONST), ";
	}
}
