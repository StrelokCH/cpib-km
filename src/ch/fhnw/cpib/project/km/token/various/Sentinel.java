package ch.fhnw.cpib.project.km.token.various;

import ch.fhnw.cpib.project.km.token.Base;

public class Sentinel extends Base {

	public Sentinel() {
		super("");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new Sentinel();
	}

}
