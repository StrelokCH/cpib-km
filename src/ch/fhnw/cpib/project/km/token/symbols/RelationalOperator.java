package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.various.Operator;

public abstract class RelationalOperator extends Operator {

	public RelationalOperator(String regex) {
		super(regex);
	}

	@Override
	public String toString() {
		return "RELOPR, ";
	}
}
