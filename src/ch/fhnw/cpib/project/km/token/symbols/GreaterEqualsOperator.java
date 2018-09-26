package ch.fhnw.cpib.project.km.token.symbols;

public class GreaterEqualsOperator extends RelationalOperator {

	@Override
	public String toString() {
		return "(" + super.toString() + "GE), ";
	}
}
