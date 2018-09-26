package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public class MechmodeReference extends Mechmode {

	public MechmodeReference() {
		super("ref");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new MechmodeReference();
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "ref), ";
	}
}
