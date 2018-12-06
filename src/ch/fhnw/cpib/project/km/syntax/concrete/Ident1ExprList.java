package ch.fhnw.cpib.project.km.syntax.concrete;

import org.openjdk.tools.javac.util.List;

import ch.fhnw.cpib.project.km.syntax.abst.FuntionCallExpr;
import ch.fhnw.cpib.project.km.syntax.abst.IExpression;
import ch.fhnw.cpib.project.km.syntax.abst.StoreExpr;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
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
		return new FuntionCallExpr(ident, exprList.toAbsSyn());
	}
}
