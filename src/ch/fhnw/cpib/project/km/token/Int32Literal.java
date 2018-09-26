package ch.fhnw.cpib.project.km.token;

public class Int32Literal extends Literal {

	private final int value;
	
	public Int32Literal(int value) {
		super();
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "Int32 " + value + "), ";
	}
}
