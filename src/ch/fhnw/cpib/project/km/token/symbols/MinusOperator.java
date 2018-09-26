package ch.fhnw.cpib.project.km.token.symbols;

public class MinusOperator extends AddOperator {

	@Override
	public String toString() {
		return "(" + super.toString() + "MINUS), ";
	}
}
