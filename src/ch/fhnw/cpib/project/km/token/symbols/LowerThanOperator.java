package ch.fhnw.cpib.project.km.token.symbols;

public class LowerThanOperator extends RelationalOperator {

	@Override
	public String toString() {
		return "(" + super.toString() + "LT), ";
	}
}
