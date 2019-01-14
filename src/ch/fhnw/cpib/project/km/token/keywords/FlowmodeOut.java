package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class FlowmodeOut extends Flowmode {

	public FlowmodeOut() {
		super("out");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new FlowmodeOut();
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "OUT), ";
	}
}
