package ch.fhnw.cpib.project.km.token.various;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class Sentinel extends BaseToken {

	public Sentinel() {
		super("");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new Sentinel();
	}

}
