package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.syntax.abst.DyadicExpr;
import ch.fhnw.cpib.project.km.syntax.abst.IExpression;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class Term2Term3 implements ITerm2 {
	private final ITerm3 term3;
	private final IRepAddoprTerm3 repAddoprTerm3;

	public Term2Term3(final ITerm3 term3, final IRepAddoprTerm3 repAddoprTerm3) {
		this.term3 = term3;
		this.repAddoprTerm3 = repAddoprTerm3;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		term3.print(indent + "    ");
		repAddoprTerm3.print(indent + "    ");
	}

	@Override
	public IExpression toAbsSyn() {
		return repAddoprTerm3.toAbsSyn(term3.toAbsSyn());
	}

	@Override
	public IExpression toAbsSyn(AddOperator addopr, IExpression expr) {
		return repAddoprTerm3.toAbsSyn(new DyadicExpr(addopr, expr, term3.toAbsSyn()));
	}
}
