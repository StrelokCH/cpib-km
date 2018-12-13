package ch.fhnw.cpib.project.km.syntax.concrete;

import java.util.List;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class GlobInitsInit implements IGlobInits {
	private final Init init;
	private final IIdents idents;

	public GlobInitsInit(final Init init, final IIdents idents) {
		this.init = init;
		this.idents = idents;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		System.out.println(indent + "    T " + init.toString());
		idents.print(indent + "    ");
	}

	@Override
	public List<Identifier> toAbsSyn() {
		return idents.toAbsSyn();
	}
}
