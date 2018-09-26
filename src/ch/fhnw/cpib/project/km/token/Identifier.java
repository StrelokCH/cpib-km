package ch.fhnw.cpib.project.km.token;

public class Identifier extends Base {

	private final String identifier;
	
	public Identifier(String identifier) {
		super();
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return identifier;
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "\"" + identifier + "\"), ";
	}
}
