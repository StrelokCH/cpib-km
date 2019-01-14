package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;
import ch.fhnw.cpib.project.km.token.symbols.MultiplicationOperator;

public class ModuloOperator extends MultiplicationOperator {

	public ModuloOperator() {
		super("modE");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new ModuloOperator();
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "MOD_E), ";
	}
}
