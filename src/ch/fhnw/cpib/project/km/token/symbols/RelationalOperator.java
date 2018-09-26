package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.BaseToken;

public abstract class RelationalOperator extends BaseToken {

	public RelationalOperator(String regex) {
		super(regex);
	}

	@Override
	public String toString() {
		return "RELOPR, ";
	}
}
