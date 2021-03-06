package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.syntax.abst.DyadicExpr;
import ch.fhnw.cpib.project.km.syntax.abst.IExpression;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class RepMultoprFactorMultopr implements IRepMultoprFactor {
	private final MultiplicationOperator multopr;
	private final ITerm3 term3;

	public RepMultoprFactorMultopr(final MultiplicationOperator multopr, final ITerm3 term3) {
		this.multopr = multopr;
		this.term3 = term3;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		System.out.println(indent + "    T " + multopr.toString());
		term3.print(indent + "    ");
	}

	@Override
	public IExpression toAbsSyn(IExpression expr) {
		return term3.toAbsSyn(multopr, expr);
	}
}
