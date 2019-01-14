package ch.fhnw.cpib.project.km.token;

public interface ITokenList {
	/**
	 * for constructing the token list by the scanner
	 * @param token
	 */
	void add(BaseToken token);

	/**
	 * for reading the token list by the parser (1)
	 */
	void reset();

	/**
	 * for reading the token list by the parser (2)
	 * @return
	 */
	BaseToken nextToken();

	/**
	 * for displaying the token list
	 * @return
	 */
	String toString();
}
