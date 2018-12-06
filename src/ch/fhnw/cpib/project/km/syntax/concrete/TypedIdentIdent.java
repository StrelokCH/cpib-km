package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.syntax.abst.FullIdentifier;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class TypedIdentIdent implements ITypedIdent {
	private final Identifier ident;
	private final Colon colon;
	private final Type atomtype;

	public TypedIdentIdent(final Identifier ident, final Colon colon, final Type atomtype) {
		this.ident = ident;
		this.colon = colon;
		this.atomtype = atomtype;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		System.out.println(indent + "    T " + ident.toString());
		System.out.println(indent + "    T " + colon.toString());
		System.out.println(indent + "    T " + atomtype.toString());
	}

	@Override
	public FullIdentifier toAbsSyn() {
		return toAbsSyn(null, null, null);
	}

	@Override
	public FullIdentifier toAbsSyn(Flowmode flowmode, Mechmode mechmode, Changemode changemode) {
		return new FullIdentifier(flowmode, mechmode, changemode, ident, atomtype);
	}
}
