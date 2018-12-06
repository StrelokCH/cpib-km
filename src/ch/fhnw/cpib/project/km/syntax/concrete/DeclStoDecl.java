package ch.fhnw.cpib.project.km.syntax.concrete;

import java.util.List;

import ch.fhnw.cpib.project.km.syntax.abst.StoDecl;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class DeclStoDecl implements IDecl {
	private final IStoDecl stoDecl;

	public DeclStoDecl(final IStoDecl stoDecl) {
		this.stoDecl = stoDecl;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		stoDecl.print(indent + "    ");
	}

	@Override
	public StoDecl toAbsSyn() {
		return stoDecl.toAbsSyn();
	}
}
