package ch.fhnw.cpib.project.km.token.symbols;

public class NotEqualsOperator extends RelationalOperator {

	@Override
	public String toString() {
		return "(" + super.toString() + "NE), ";
	}
}
