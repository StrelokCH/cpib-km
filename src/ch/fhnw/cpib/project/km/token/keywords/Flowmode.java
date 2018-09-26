package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public abstract class Flowmode extends Base {

	public Flowmode(String regex) {
		super(regex);		
	}

	@Override
	public String toString() {
		return "FLOWMODE, ";
	}
}
