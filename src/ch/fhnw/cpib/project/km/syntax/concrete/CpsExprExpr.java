package ch.fhnw.cpib.project.km.syntax.concrete;

import java.util.List;

import ch.fhnw.cpib.project.km.syntax.abst.IExpression;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class CpsExprExpr implements ICpsExpr {
	private final IExpr expr;
	private final IRepCommaExpr repCommaExpr;

	public CpsExprExpr(final IExpr expr, final IRepCommaExpr repCommaExpr) {
		this.expr = expr;
		this.repCommaExpr = repCommaExpr;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		expr.print(indent + "    ");
		repCommaExpr.print(indent + "    ");
	}

	@Override
	public List<IExpression> toAbsSyn() {
		List<IExpression> list = repCommaExpr.toAbsSyn();
		list.add(0, expr.toAbsSyn());
		return list;
	}
}
