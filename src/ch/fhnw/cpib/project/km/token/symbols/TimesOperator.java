package ch.fhnw.cpib.project.km.token.symbols;

public class TimesOperator extends MultiplicationOperator {

	@Override
	public String toString() {
		return "(" + super.toString() + "TIMES), ";
	}
}
