package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.syntax.abst.FunctionCallExpr;
import ch.fhnw.cpib.project.km.syntax.abst.IExpression;
import ch.fhnw.cpib.project.km.token.various.*;

public class Ident1ExprList implements IIdent1 {
	private final IExprList exprList;

	public Ident1ExprList(final IExprList exprList) {
		this.exprList = exprList;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		exprList.print(indent + "    ");
	}

	@Override
	public IExpression toAbsSyn(Identifier ident) {
		return new FunctionCallExpr(ident, exprList.toAbsSyn());
	}
}
