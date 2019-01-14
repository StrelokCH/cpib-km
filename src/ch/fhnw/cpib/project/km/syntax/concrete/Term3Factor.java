package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.syntax.abst.DyadicExpr;
import ch.fhnw.cpib.project.km.syntax.abst.IExpression;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class Term3Factor implements ITerm3 {
	private final IFactor factor;
	private final IRepMultoprFactor repMultoprFactor;

	public Term3Factor(final IFactor factor, final IRepMultoprFactor repMultoprFactor) {
		this.factor = factor;
		this.repMultoprFactor = repMultoprFactor;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		factor.print(indent + "    ");
		repMultoprFactor.print(indent + "    ");
	}

	@Override
	public IExpression toAbsSyn() {
		return repMultoprFactor.toAbsSyn(factor.toAbsSyn());
	}

	@Override
	public IExpression toAbsSyn(MultiplicationOperator multopr, IExpression expr) {
		return repMultoprFactor.toAbsSyn(new DyadicExpr(multopr, expr, factor.toAbsSyn()));
	}
}
