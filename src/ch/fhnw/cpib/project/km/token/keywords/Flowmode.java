package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;

public abstract class Flowmode extends BaseToken {

	public Flowmode(String regex) {
		super(regex);		
	}

	@Override
	public String toString() {
		return "FLOWMODE, ";
	}
}
