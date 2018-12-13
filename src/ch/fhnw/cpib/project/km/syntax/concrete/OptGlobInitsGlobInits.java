package ch.fhnw.cpib.project.km.syntax.concrete;

import java.util.List;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class OptGlobInitsGlobInits implements IOptGlobInits {
	private final IGlobInits globInits;

	public OptGlobInitsGlobInits(final IGlobInits globInits) {
		this.globInits = globInits;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		globInits.print(indent + "    ");
	}

	@Override
	public List<Identifier> toAbsSyn() {
		return globInits.toAbsSyn();
	}
}
