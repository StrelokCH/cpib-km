package ch.fhnw.cpib.project.km.token;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class BaseToken {

	/**
	 * Pattern (Regex) that this token matches
	 */
	private final Pattern pattern;

	public BaseToken(String regex) {
		super();
		this.pattern = Pattern.compile(regex);
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName().toUpperCase() + ", ";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		return obj.getClass().equals(this.getClass());
	}

	/**
	 *
	 * @return Number of characters that match this token
	 */
	public int match(CharSequence s) {
		Matcher m = pattern.matcher(s);
		if (m.lookingAt()) {
			return m.end();
		}
		return 0;
	}

	/**
	 * Creates a new Instance of this class based on the given CharSequence
	 * 
	 * @param s
	 * @return if no instance is resulting from s then null is returned
	 */
	public BaseToken createToken(CharSequence s) {
		int length = match(s);
		if (length <= 0) {
			throw new IllegalArgumentException("Token can't be created from the given Argument.");
		}

		return internalCreateToken(s.subSequence(0, length).toString());
	}

	/**
	 * see createToken
	 * 
	 * @param s
	 * @return
	 */
	protected abstract BaseToken internalCreateToken(String s);
}
