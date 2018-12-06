package ch.fhnw.cpib.project.km.syntax.concrete;

import java.util.List;

import ch.fhnw.cpib.project.km.syntax.abst.StoDecl;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class RepSemicolonCpsStoDeclSemicolon implements IRepSemicolonCpsStoDecl {
	private final Semicolon semicolon;
	private final ICpsStoDecl cpsStoDecl;

	public RepSemicolonCpsStoDeclSemicolon(final Semicolon semicolon, final ICpsStoDecl cpsStoDecl) {
		this.semicolon = semicolon;
		this.cpsStoDecl = cpsStoDecl;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		System.out.println(indent + "    T " + semicolon.toString());
		cpsStoDecl.print(indent + "    ");
	}

	@Override
	public List<StoDecl> toAbsSyn() {
		return cpsStoDecl.toAbsSyn();
	}
}
