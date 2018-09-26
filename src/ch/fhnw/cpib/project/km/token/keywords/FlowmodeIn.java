package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public class FlowmodeIn extends Flowmode {

	public FlowmodeIn() {
		super("in");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new FlowmodeIn();
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "IN), ";
	}
}
