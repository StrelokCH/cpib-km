package ch.fhnw.cpib.project.km.syntax.concrete;

import java.util.List;

import ch.fhnw.cpib.project.km.syntax.abst.FullIdentifier;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class RepSemicolonCpsDeclSemicolon implements IRepSemicolonCpsDecl {
	private final Semicolon semicolon;
	private final ICpsDecl cpsDecl;

	public RepSemicolonCpsDeclSemicolon(final Semicolon semicolon, final ICpsDecl cpsDecl) {
		this.semicolon = semicolon;
		this.cpsDecl = cpsDecl;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		System.out.println(indent + "    T " + semicolon.toString());
		cpsDecl.print(indent + "    ");
	}

	@Override
	public List<ch.fhnw.cpib.project.km.syntax.abst.IDecl> toAbsSyn() {
		return cpsDecl.toAbsSyn();
	}
}
