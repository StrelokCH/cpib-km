package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.Base;

public abstract class RelationalOperator extends Base {

	public RelationalOperator(String regex) {
		super(regex);
	}

	@Override
	public String toString() {
		return "RELOPR, ";
	}
}
