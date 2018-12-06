package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.syntax.abst.DyadicExpr;
import ch.fhnw.cpib.project.km.syntax.abst.IExpression;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class Term1Term2 implements ITerm1 {
	private final ITerm2 term2;
	private final IOptReloprTerm2 optReloprTerm2;

	public Term1Term2(final ITerm2 term2, final IOptReloprTerm2 optReloprTerm2) {
		this.term2 = term2;
		this.optReloprTerm2 = optReloprTerm2;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		term2.print(indent + "    ");
		optReloprTerm2.print(indent + "    ");
	}

	@Override
	public IExpression toAbsSyn() {
		return optReloprTerm2.toAbsSyn(term2.toAbsSyn());
	}
}
