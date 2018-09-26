package ch.fhnw.cpib.project.km.token.symbols;

public class CAndOperator extends BooleanOperator {

	@Override
	public String toString() {
		return "(" + super.toString() + "CAND), ";
	}
}
