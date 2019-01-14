package ch.fhnw.cpib.project.km.syntax.concrete;

import java.util.List;

import ch.fhnw.cpib.project.km.syntax.abst.FullIdentifier;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class GlobalGlobImpsGlobal implements IGlobalGlobImps {
	private final Global global;
	private final IGlobImps globImps;

	public GlobalGlobImpsGlobal(final Global global, final IGlobImps globImps) {
		this.global = global;
		this.globImps = globImps;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		System.out.println(indent + "    T " + global.toString());
		globImps.print(indent + "    ");
	}

	@Override
	public List<FullIdentifier> toAbsSyn() {
		return globImps.toAbsSyn();
	}
}
