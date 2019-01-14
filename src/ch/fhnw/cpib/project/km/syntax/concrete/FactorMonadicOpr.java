package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.syntax.abst.IExpression;
import ch.fhnw.cpib.project.km.syntax.abst.MonadicExpr;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class FactorMonadicOpr implements IFactor {
	private final IMonadicOpr monadicOpr;
	private final IFactor factor;

	public FactorMonadicOpr(final IMonadicOpr monadicOpr, final IFactor factor) {
		this.monadicOpr = monadicOpr;
		this.factor = factor;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		monadicOpr.print(indent + "    ");
		factor.print(indent + "    ");
	}

	@Override
	public IExpression toAbsSyn() {
		return new MonadicExpr(monadicOpr.toAbsSyn(), factor.toAbsSyn());
	}
}
