package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.syntax.abst.StoDecl;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class StoDeclChangemode implements IStoDecl {
	private final Changemode changemode;
	private final ITypedIdent typedIdent;

	public StoDeclChangemode(final Changemode changemode, final ITypedIdent typedIdent) {
		this.changemode = changemode;
		this.typedIdent = typedIdent;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		System.out.println(indent + "    T " + changemode.toString());
		typedIdent.print(indent + "    ");
	}

	@Override
	public StoDecl toAbsSyn() {
		return new StoDecl(typedIdent.toAbsSyn(null, null, changemode));
	}
}
