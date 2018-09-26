package ch.fhnw.cpib.project.km.token;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class TokenList implements ITokenList {
	private Iterator<BaseToken> current = null;
	private final List<BaseToken> tokens = new ArrayList<>();

	@Override
	public void add(BaseToken token) {
		if (token == null) {
			throw new IllegalArgumentException("null not permitted");
		}
		
		tokens.add(token);
		current = null;
	}

	@Override
	public void reset() {
		current = tokens.iterator();
	}

	@Override
	public BaseToken nextToken() {
		if (current == null) {
			reset();
		}
		if (!current.hasNext()) {
			return null;
		}
		return current.next();
	}

	@Override
	public String toString() {
		return tokens.stream().map(t -> t.toString()).collect(Collectors.joining());
	}
}
