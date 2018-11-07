package ch.fhnw.cpib.project.km.token.various;

import ch.fhnw.cpib.project.km.token.BaseToken;

public class Int32Literal extends Literal {

	private final int value;

	public Int32Literal(int value) {
		// unsigned
		super("\\d('*\\d+)*");
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	/**
	 * Special match functionality as the size of the number must be checked
	 * 
	 * @return Number of characters that match this token
	 */
	public int match(CharSequence s) {
		int length = super.match(s);
		if (length > 0) {
			String matchedCharSequence = s.subSequence(0, length).toString();
			long longValue = Long.parseLong(matchedCharSequence);
			int intValue = Integer.parseInt(matchedCharSequence);
			if ((long) intValue == longValue) {
				// value fits in an integer and is therefore 32bit
				return length;
			}

		}
		// no match or too big value
		return 0;
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "Int32 " + value + "), ";
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj) && ((Int32Literal) obj).value == this.value;
	}

	@Override
	protected BaseToken internalCreateToken(String s) {
		// TODO check if signed/unsigned
		return new Int32Literal(Integer.parseInt(s));
	}
}
