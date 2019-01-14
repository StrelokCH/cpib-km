package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.various.Identifier;
import ch.fhnw.cpib.project.km.syntax.abst.*;

import java.util.List;



public class CmdCall implements ICmd {
	private final Call call;
	private final Identifier ident;
	private final IExprList exprList;
	private final IOptGlobInits optGlobInits;

	public CmdCall(
			final Call call,
			final Identifier ident,
			final IExprList exprList,
			final IOptGlobInits optGlobInits) {
		this.call = call;
		this.ident = ident;
		this.exprList = exprList;
		this.optGlobInits = optGlobInits;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		System.out.println(indent + "    T " + call.toString());
		System.out.println(indent + "    T " + ident.toString());
		exprList.print(indent + "    ");
		optGlobInits.print(indent + "    ");
	}

	@Override
	public ICommand toAbsSyn() {
		return new ProcCallCmd(ident, exprList.toAbsSyn(), optGlobInits.toAbsSyn());
	}
}
