package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class OptMechmodeMechmode implements IOptMechmode {
	private final Mechmode mechmode;

	public OptMechmodeMechmode(final Mechmode mechmode) {
		this.mechmode = mechmode;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		System.out.println(indent + "    T " + mechmode.toString());
	}

	@Override
	public Mechmode toAbsSyn() {
		return mechmode;
	}
}
