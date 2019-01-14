package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class OptChangemodeChangemode implements IOptChangemode {
	private final Changemode changemode;

	public OptChangemodeChangemode(final Changemode changemode) {
		this.changemode = changemode;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		System.out.println(indent + "    T " + changemode.toString());
	}

	@Override
	public Changemode toAbsSyn() {
		return changemode;
	}
}
