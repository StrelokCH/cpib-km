package ch.fhnw.cpib.project.km.syntax.concrete;

import java.util.List;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class IdentsIdent implements IIdents {
	private final Identifier ident;
	private final IRepCommaIdent repCommaIdent;

	public IdentsIdent(final Identifier ident, final IRepCommaIdent repCommaIdent) {
		this.ident = ident;
		this.repCommaIdent = repCommaIdent;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		System.out.println(indent + "    T " + ident.toString());
		repCommaIdent.print(indent + "    ");
	}

	@Override
	public List<Identifier> toAbsSyn() {
		List<Identifier> l = repCommaIdent.toAbsSyn();
		l.add(0, ident);
		return null;
	}
}
