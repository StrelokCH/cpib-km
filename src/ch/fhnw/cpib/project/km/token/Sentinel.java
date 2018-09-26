package ch.fhnw.cpib.project.km.token;

public class Sentinel extends Base {

	public Sentinel() {
		super("");
	}

	@Override
	protected Base internalCreateToken(String s) {
		return new Sentinel();
	}

}
