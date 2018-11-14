package ch.fhnw.cpib.project.km.parser;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import ch.fhnw.cpib.project.km.exceptions.GrammarError;
import ch.fhnw.cpib.project.km.exceptions.LexicalErrorException;
import ch.fhnw.cpib.project.km.scanner.Scanner;
import ch.fhnw.cpib.project.km.syntax.concrete.IProgram;
import ch.fhnw.cpib.project.km.token.ITokenList;

class ParserTest {
	@Test
	void testParse() {
		// IML Example Program (Basic IML)
		// from cpib_HS-2018_Slides_IML_V1.pdf page 5
		String input = "program intDiv(in const m:int64, in const n:int64,\r\n"
				+ "out const q:int64, out const r:int64)\r\n" + "global\r\n"
				+ "proc divide(in copy const m:int64, in copy const n:int64,\r\n"
				+ "out ref var q:int64, out ref var r:int64)\r\n" + "do\r\n" + "q init := 0;\r\n" + "r init := m;\r\n"
				+ "while r >= n do\r\n" + "q := q + 1;\r\n" + "r := r - n\r\n" + "endwhile\r\n" + "endproc\r\n"
				+ "do\r\n" + "call divide(m, n, q init, r init)\r\n" + "endprogram\n";
		
		ITokenList tokens;
		try {
			tokens = Scanner.scan(input);
			IProgram program = new Parser(tokens).parse();
		} catch (LexicalErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GrammarError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
