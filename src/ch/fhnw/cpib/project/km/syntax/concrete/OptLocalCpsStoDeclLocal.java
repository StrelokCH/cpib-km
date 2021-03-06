package ch.fhnw.cpib.project.km.syntax.concrete;

import java.util.List;

import ch.fhnw.cpib.project.km.syntax.abst.FullIdentifier;
import ch.fhnw.cpib.project.km.syntax.abst.StoDecl;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class OptLocalCpsStoDeclLocal implements IOptLocalCpsStoDecl {
	private final Local local;
	private final ICpsStoDecl cpsStoDecl;

	public OptLocalCpsStoDeclLocal(final Local local, final ICpsStoDecl cpsStoDecl) {
		this.local = local;
		this.cpsStoDecl = cpsStoDecl;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		System.out.println(indent + "    T " + local.toString());
		cpsStoDecl.print(indent + "    ");
	}

	@Override
	public List<StoDecl> toAbsSyn() {
		return cpsStoDecl.toAbsSyn();
	}
}
