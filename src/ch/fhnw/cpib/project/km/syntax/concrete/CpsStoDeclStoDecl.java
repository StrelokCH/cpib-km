package ch.fhnw.cpib.project.km.syntax.concrete;

import java.util.List;

import ch.fhnw.cpib.project.km.syntax.abst.FullIdentifier;
import ch.fhnw.cpib.project.km.syntax.abst.StoDecl;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class CpsStoDeclStoDecl implements ICpsStoDecl {
	private final IStoDecl stoDecl;
	private final IRepSemicolonCpsStoDecl repSemicolonCpsStoDecl;

	public CpsStoDeclStoDecl(final IStoDecl stoDecl, final IRepSemicolonCpsStoDecl repSemicolonCpsStoDecl) {
		this.stoDecl = stoDecl;
		this.repSemicolonCpsStoDecl = repSemicolonCpsStoDecl;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		stoDecl.print(indent + "    ");
		repSemicolonCpsStoDecl.print(indent + "    ");
	}

	@Override
	public List<StoDecl> toAbsSyn() {
		List<StoDecl> ret = repSemicolonCpsStoDecl.toAbsSyn();
		ret.add(0, stoDecl.toAbsSyn());
		return null;
	}
}
