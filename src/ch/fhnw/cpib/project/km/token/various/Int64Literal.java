package ch.fhnw.cpib.project.km.token.various;

import java.math.BigInteger;
import java.util.regex.Matcher;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class Int64Literal extends Literal {

	private final long value;
	
	public Int64Literal(long value) {
		// unsigned
		super("\\\\d('*\\\\d+)*");
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
			long longValue = Long.parseLong(matchedCharSequence);
			BigInteger bigIntValue = new BigInteger(matchedCharSequence);
			if (BigInteger.valueOf(longValue) == bigIntValue) {
				// value fits in a long and is therefore 64bit
				return length;
			}
			
		}
		// no match or too big value
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
		// TODO check if singed/unsigned
		return new Int64Literal(Long.parseLong(s));
	}
}
