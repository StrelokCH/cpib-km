package ch.fhnw.cpib.project.km.token.symbols;

import ch.fhnw.cpib.project.km.token.BaseToken;

/**
 * Conditional Or
 * @author Janik
 *
 */
public class COrOperator extends BooleanOperator {

	public COrOperator() {
		super("\\|\\?");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new COrOperator();
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "COR), ";
	}
}
