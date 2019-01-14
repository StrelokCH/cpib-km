package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.syntax.abst.IExpression;
import ch.fhnw.cpib.project.km.syntax.abst.StoreExpr;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class Ident1Init implements IIdent1 {
	private final Init init;

	public Ident1Init(final Init init) {
		this.init = init;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		System.out.println(indent + "    T " + init.toString());
	}

	@Override
	public IExpression toAbsSyn(Identifier ident) {
		return new StoreExpr(ident, true);
	}
}
