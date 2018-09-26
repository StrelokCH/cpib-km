package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public class MechmodeCopy extends Mechmode {

	public MechmodeCopy() {
		super("copy");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new MechmodeCopy();
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "COPY), ";
	}
}
