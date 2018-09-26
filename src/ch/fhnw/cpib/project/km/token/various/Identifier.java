package ch.fhnw.cpib.project.km.token.various;

import ch.fhnw.cpib.project.km.token.Base;

public class Identifier extends Base {

	private final String identifier;
	
	public Identifier(String identifier) {
		super("[a-zA-Z]([\\w'])*");
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return identifier;
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "\"" + identifier + "\"), ";
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new Identifier(s);
	}
}
