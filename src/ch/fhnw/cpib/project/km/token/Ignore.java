package ch.fhnw.cpib.project.km.token;

public class Ignore extends Base {

	public Ignore() {
		super("[\\n\\s]+");
	}

	@Override
	protected Base internalCreateToken(String s) {
		// no Token will be created as spaces and newlines are ignored
		return null;
	}
}
