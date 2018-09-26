package ch.fhnw.cpib.project.km.token.symbols;

public class GreaterThanOperator extends RelationalOperator {

	@Override
	public String toString() {
		return "(" + super.toString() + "GT), ";
	}
}
