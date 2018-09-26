package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class MechmodeReference extends Mechmode {

	public MechmodeReference() {
		super("ref");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new MechmodeReference();
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "ref), ";
	}
}
