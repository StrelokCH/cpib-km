package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public class Var extends ChangeMode {

	public Var() {
		super("var");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new Var();
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "VAR), ";
	}
}
