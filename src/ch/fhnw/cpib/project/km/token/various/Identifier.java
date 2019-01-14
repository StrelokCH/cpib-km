package ch.fhnw.cpib.project.km.token.various;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class Identifier extends BaseToken {

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
	public boolean equals(Object obj) {
		return super.equals(obj) && ((Identifier)obj).identifier.equals(this.identifier);
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new Identifier(s);
	}
}
