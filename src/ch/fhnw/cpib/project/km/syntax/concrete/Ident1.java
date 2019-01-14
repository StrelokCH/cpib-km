package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.syntax.abst.IExpression;
import ch.fhnw.cpib.project.km.syntax.abst.StoreExpr;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class Ident1 implements IIdent1 {
	public Ident1() {
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
	}

	@Override
	public IExpression toAbsSyn(Identifier ident) {
		return new StoreExpr(ident, false);
	}
}
