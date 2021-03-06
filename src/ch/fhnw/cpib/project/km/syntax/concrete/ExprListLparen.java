package ch.fhnw.cpib.project.km.syntax.concrete;

import java.util.List;

import ch.fhnw.cpib.project.km.syntax.abst.IExpression;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class ExprListLparen implements IExprList {
	private final LeftParenthesis lparen;
	private final IOptCpsExpr optCpsExpr;
	private final RightParenthesis rparen;

	public ExprListLparen(final LeftParenthesis lparen, final IOptCpsExpr optCpsExpr, final RightParenthesis rparen) {
		this.lparen = lparen;
		this.optCpsExpr = optCpsExpr;
		this.rparen = rparen;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		System.out.println(indent + "    T " + lparen.toString());
		optCpsExpr.print(indent + "    ");
		System.out.println(indent + "    T " + rparen.toString());
	}

	@Override
	public List<IExpression> toAbsSyn() {
		return optCpsExpr.toAbsSyn();
	}
}
