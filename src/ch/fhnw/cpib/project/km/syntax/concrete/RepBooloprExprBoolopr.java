package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.syntax.abst.IExpression;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class RepBooloprExprBoolopr implements IRepBooloprExpr {
	private final BooleanOperator boolopr;
	private final IExpr expr;

	public RepBooloprExprBoolopr(final BooleanOperator boolopr, final IExpr expr) {
		this.boolopr = boolopr;
		this.expr = expr;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		System.out.println(indent + "    T " + boolopr.toString());
		expr.print(indent + "    ");
	}

	@Override
	public IExpression toAbsSyn(IExpression expr) {
		return this.expr.toAbsSyn(boolopr, expr);
	}
}
