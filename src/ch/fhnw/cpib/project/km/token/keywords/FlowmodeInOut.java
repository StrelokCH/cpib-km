package ch.fhnw.cpib.project.km.token.keywords;

import ch.fhnw.cpib.project.km.token.Base;

public class FlowmodeInOut extends Flowmode {

	public FlowmodeInOut() {
		super("inout");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new FlowmodeInOut();
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "INOUT), ";
	}
}
