package ch.fhnw.cpib.project.km.token;

public class Int64Literal extends Literal {

	private final int value;
	
	public Int64Literal(int value) {
		super();
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "Int64 " + value + "), ";
	}
}
