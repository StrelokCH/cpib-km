package ch.fhnw.cpib.project.km.token;

public abstract class Literal extends Base {

	public Literal(String regex) {
		super(regex);
	}

	@Override
	public String toString() {
		return "LITERAL, ";
	}
}
