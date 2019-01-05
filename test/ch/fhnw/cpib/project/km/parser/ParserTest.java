package ch.fhnw.cpib.project.km.parser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import ch.fhnw.cpib.project.km.base.AbstractTest;
import ch.fhnw.cpib.project.km.code.IMLTestPrograms;
import ch.fhnw.cpib.project.km.exceptions.GrammarException;
import ch.fhnw.cpib.project.km.exceptions.LexicalErrorException;
import ch.fhnw.cpib.project.km.scanner.Scanner;
import ch.fhnw.cpib.project.km.token.ITokenList;

class ParserTest extends AbstractTest {

	@Override
	protected Object test(String input) throws Exception {
		try {
			ITokenList tokens = Scanner.scan(input);
			return new Parser(tokens).parse(true);
		} catch (LexicalErrorException e) {
			fail(e);
		}
		return null;
	}

	@Test
	void testParse_Invalid() {

		String input = IMLTestPrograms.DearGrandmother;

		ITokenList tokens;
		try {
			tokens = Scanner.scan(input);
			Executable exe = () -> new Parser(tokens).parse(true);
			assertThrows(GrammarException.class, exe);
		} catch (LexicalErrorException e) {
			fail(e);
		}
	}
}
