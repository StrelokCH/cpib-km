package ch.fhnw.cpib.project.km.token;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Base {

	/**
	 * Pattern (Regex) that this token matches
	 */
	private final Pattern pattern;
	
	public Base(String regex) {
		super();
		this.pattern = Pattern.compile(regex);
	}
	
	public String toString() {
		return this.getClass().getSimpleName().toUpperCase() + ", ";
	}
	
	/**
	 *
	 * @return Number of characters that match this token
	 */
	public int match(CharSequence s) {
		Matcher m = pattern.matcher(s);
		m.find();
		return m.end();
	}
	
	/**
	 * Creates a new Instance of this class based on the given CharSequence
	 * @param s
	 * @return if no instance is resulting from s then null is returned
	 */
	public Base createToken(CharSequence s) {
		int length = match(s);
		if (length <= 0) {
			throw new IllegalArgumentException("Token can't be created from the given Argument.");
		}
		
		return internalCreateToken(s.subSequence(0, length).toString());
	}
	
	/**
	 * see createToken
	 * @param s
	 * @return 
	 */
	protected abstract Base internalCreateToken(String s);
}
