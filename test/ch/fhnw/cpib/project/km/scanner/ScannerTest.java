package ch.fhnw.cpib.project.km.scanner;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import ch.fhnw.cpib.project.km.base.AbstractTest;
import ch.fhnw.cpib.project.km.code.IMLTestPrograms;
import ch.fhnw.cpib.project.km.exceptions.LexicalErrorException;
import ch.fhnw.cpib.project.km.token.ITokenList;
import ch.fhnw.cpib.project.km.token.TokenList;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

class ScannerTest extends AbstractTest {

	@Test
	void testScan_Simple() {
		String input = "while x36 <=67 do\n\r            x := x-1\n        endwhile\n";

		ITokenList expected = new TokenList();
		expected.add(new While());
		expected.add(new Identifier("x36"));
		expected.add(new LowerEqualsOperator());
		expected.add(new Int32Literal(67));
		expected.add(new Do());
		expected.add(new Identifier("x"));
		expected.add(new Becomes());
		expected.add(new Identifier("x"));
		expected.add(new MinusOperator());
		expected.add(new Int32Literal(1));
		expected.add(new EndWhile());
		expected.add(new Sentinel());

		try {
			assertEquals(expected, Scanner.scan(input));
		} catch (LexicalErrorException e) {
			fail(e);
		}
	}

	@Test
	void testScan_Whitespace() {
		ITokenList expected = new TokenList();
		expected.add(new Sentinel());

		try {
			assertEquals(expected, Scanner.scan("  \n\r \n \r \n"));
		} catch (LexicalErrorException e) {
			fail(e);
		}
	}

	@Test
	void testScan_Empty() {
		ITokenList expected = new TokenList();
		expected.add(new Sentinel());

		try {
			assertEquals(expected, Scanner.scan(""));
		} catch (LexicalErrorException e) {
			fail(e);
		}
	}

	@Test
	void testScan_LexicalError() {
		try {
			Scanner.scan("\tasdf\n");
			fail("should throw a LexicalErrorException");
		} catch (LexicalErrorException e) {
		}
	}

	@Test
	void testScan_Text() {
		String input = IMLTestPrograms.DearGrandmother;
		ITokenList expected = new TokenList();
		expected.add(new Identifier("Liebe"));
		expected.add(new Identifier("Grossmutter"));
		expected.add(new Semicolon());
		expected.add(new RightParenthesis());
		expected.add(new Identifier("Zu"));
		expected.add(new Identifier("Deinem"));
		expected.add(new Int32Literal(67));
		expected.add(new MinusOperator());
		expected.add(new Identifier("ten"));
		expected.add(new Identifier("Geburtstag"));
		expected.add(new Sentinel());

		try {
			assertEquals(expected, Scanner.scan(input));
		} catch (LexicalErrorException e) {
			fail(e);
		}
	}

	@Test
	void testScan_IntDivProgram() {
		String input = IMLTestPrograms.IntDiv64;

		ITokenList expected = new TokenList();

		expected.add(new Program());
		expected.add(new Identifier("intDiv64"));
		expected.add(new LeftParenthesis());
		expected.add(new FlowmodeIn());
		expected.add(new Const());
		expected.add(new Identifier("m"));
		expected.add(new Colon());
		expected.add(new Int64());
		expected.add(new Comma());
		expected.add(new FlowmodeIn());
		expected.add(new Const());
		expected.add(new Identifier("n"));
		expected.add(new Colon());
		expected.add(new Int64());
		expected.add(new Comma());

		expected.add(new FlowmodeOut());
		expected.add(new Const());
		expected.add(new Identifier("q"));
		expected.add(new Colon());
		expected.add(new Int64());
		expected.add(new Comma());
		expected.add(new FlowmodeOut());
		expected.add(new Const());
		expected.add(new Identifier("r"));
		expected.add(new Colon());
		expected.add(new Int64());
		expected.add(new RightParenthesis());

		expected.add(new Global());

		expected.add(new Procedure());
		expected.add(new Identifier("divide"));
		expected.add(new LeftParenthesis());
		expected.add(new FlowmodeIn());
		expected.add(new MechmodeCopy());
		expected.add(new Const());
		expected.add(new Identifier("m"));
		expected.add(new Colon());
		expected.add(new Int64());
		expected.add(new Comma());
		expected.add(new FlowmodeIn());
		expected.add(new MechmodeCopy());
		expected.add(new Const());
		expected.add(new Identifier("n"));
		expected.add(new Colon());
		expected.add(new Int64());
		expected.add(new Comma());

		expected.add(new FlowmodeOut());
		expected.add(new MechmodeReference());
		expected.add(new Var());
		expected.add(new Identifier("q"));
		expected.add(new Colon());
		expected.add(new Int64());
		expected.add(new Comma());
		expected.add(new FlowmodeOut());
		expected.add(new MechmodeReference());
		expected.add(new Var());
		expected.add(new Identifier("r"));
		expected.add(new Colon());
		expected.add(new Int64());
		expected.add(new RightParenthesis());

		expected.add(new Do());

		expected.add(new Identifier("q"));
		expected.add(new Init());
		expected.add(new Becomes());
		expected.add(new Int32Literal(0));
		expected.add(new Semicolon());

		expected.add(new Identifier("r"));
		expected.add(new Init());
		expected.add(new Becomes());
		expected.add(new Identifier("m"));
		expected.add(new Semicolon());

		expected.add(new While());
		expected.add(new Identifier("r"));
		expected.add(new GreaterEqualsOperator());
		expected.add(new Identifier("n"));
		expected.add(new Do());

		expected.add(new Identifier("q"));
		expected.add(new Becomes());
		expected.add(new Identifier("q"));
		expected.add(new PlusOperator());
		expected.add(new Int32Literal(1));
		expected.add(new Semicolon());

		expected.add(new Identifier("r"));
		expected.add(new Becomes());
		expected.add(new Identifier("r"));
		expected.add(new MinusOperator());
		expected.add(new Identifier("n"));

		expected.add(new EndWhile());

		expected.add(new EndProcedure());

		expected.add(new Do());

		expected.add(new Call());
		expected.add(new Identifier("divide"));
		expected.add(new LeftParenthesis());
		expected.add(new Identifier("m"));
		expected.add(new Comma());
		expected.add(new Identifier("n"));
		expected.add(new Comma());
		expected.add(new Identifier("q"));
		expected.add(new Init());
		expected.add(new Comma());
		expected.add(new Identifier("r"));
		expected.add(new Init());
		expected.add(new RightParenthesis());

		expected.add(new EndProgram());

		expected.add(new Sentinel());

		try {
			assertEquals(expected, Scanner.scan(input));
		} catch (LexicalErrorException e) {
			fail(e);
		}
	}

	@Override
	protected Object test(String input) throws Exception {
		return Scanner.scan(input);
	}
}
