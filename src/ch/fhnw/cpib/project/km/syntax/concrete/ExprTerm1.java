package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.syntax.abst.DyadicExpr;
import ch.fhnw.cpib.project.km.syntax.abst.IExpression;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class ExprTerm1 implements IExpr {
	private final ITerm1 term1;
	private final IRepBooloprExpr repBooloprExpr;

	public ExprTerm1(final ITerm1 term1, final IRepBooloprExpr repBooloprExpr) {
		this.term1 = term1;
		this.repBooloprExpr = repBooloprExpr;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		term1.print(indent + "    ");
		repBooloprExpr.print(indent + "    ");
	}

	@Override
	public IExpression toAbsSyn() {
		return repBooloprExpr.toAbsSyn(term1.toAbsSyn());
	}

	@Override
	public IExpression toAbsSyn(BooleanOperator boolOpr, IExpression expr) {
		return repBooloprExpr.toAbsSyn(new DyadicExpr(boolOpr, expr, term1.toAbsSyn()));
	}
}
