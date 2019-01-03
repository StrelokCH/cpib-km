package ch.fhnw.cpib.project.km.token.various;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class Comment extends BaseToken {

	public Comment() {
		super("//[^\\n]*\\n");
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		// no Token will be created as spaces and newlines are ignored
		return null;
	}
}
