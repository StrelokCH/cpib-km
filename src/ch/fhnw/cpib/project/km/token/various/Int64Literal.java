package ch.fhnw.cpib.project.km.token.various;

import java.math.BigInteger;
import ch.fhnw.cpib.project.km.token.BaseToken;

public class Int64Literal extends Literal {

	private final long value;
	
	public Int64Literal(long value) {
		// unsigned
		super("\\d('*\\d+)*");
		this.value = value;
	}

	public long getValue() {
		return value;
	}
	
	/**
	 * Special match functionality as the size of the number must be checked
	 * @return Number of characters that match this token
	 */
	public int match(CharSequence s) {
		int length = super.match(s);
		if (length > 0) {
			String matchedCharSequence = s.subSequence(0, length).toString();
			try {
				Long.parseLong(matchedCharSequence);
			} catch (NumberFormatException e){
				// number is too big
				return 0;
			}
			// no error, it is an int64
			return length;
		}
		// no match
		return 0;
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "Int64 " + value + "), ";
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj) && ((Int64Literal)obj).value == this.value;
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		return new Int64Literal(Long.parseLong(s));
	}
}
