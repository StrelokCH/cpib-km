package ch.fhnw.cpib.project.km.syntax.abst;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import ch.fhnw.cpib.project.km.analysis.StaticAnalyser;
import ch.fhnw.cpib.project.km.exceptions.GrammarException;
import ch.fhnw.cpib.project.km.exceptions.LexicalErrorException;
import ch.fhnw.cpib.project.km.parser.Parser;
import ch.fhnw.cpib.project.km.scanner.Scanner;
import ch.fhnw.cpib.project.km.syntax.concrete.IProgram;
import ch.fhnw.cpib.project.km.token.ITokenList;

public class AbstractSyntaxTest {

	@Test
	void testToAbsSyn1() {
		// IML Example Program (Basic IML)
		// from cpib_HS-2018_Slides_IML_V1.pdf page 5
		String input = "program intDiv(in const m:int64, in const n:int64,\r\n"
				+ "out const q:int64, out const r:int64)\r\n" + "global\r\n"
				+ "proc divide(in copy const m:int64, in copy const n:int64,\r\n"
				+ "out ref var q:int64, out ref var r:int64)\r\n" + "do\r\n" + "q init := 0;\r\n" + "r init := m;\r\n"
				+ "while r >= n do\r\n" + "q := q + 1;\r\n" + "r := r - n\r\n" + "endwhile\r\n" + "endproc\r\n"
				+ "do\r\n" + "call divide(m, n, q init, r init)\r\n" + "endprogram\n";

		try {
			ITokenList tokens = Scanner.scan(input);
			IProgram program = new Parser(tokens).parse();
			Program abstProgram = program.toAbsSyn();
			System.out.println(abstProgram.toString(""));
		} catch (LexicalErrorException e) {
			fail(e);
		} catch (GrammarException e) {
			fail(e);
		}
	}

	@Test
	void testToAbsSyn2() {
		String input = "program GlobalInits1()\r\n" + "global\r\n" + "var g1:int64;\r\n" + "var g2:int64;\r\n"
				+ "proc p(in copy const x:int64)\r\n" + "global out const g1, out const g2\r\n" + "do\r\n"
				+ "g1 init := x * x;\r\n" + "g2 init := -g1\r\n" + "endproc\r\n" + "do\r\n" + "call p(5) init g1, g2;"
				+ "debugout g1;" + "debugout g2;\r\n" + "call p(6);" + "debugout g1;" + "debugout g2\r\n"
				+ "endprogram\r\n";

		try {
			ITokenList tokens = Scanner.scan(input);
			IProgram program = new Parser(tokens).parse();
			Program abstProgram = program.toAbsSyn();
			System.out.println(abstProgram.toString(""));
		} catch (LexicalErrorException e) {
			fail(e);
		} catch (GrammarException e) {
			fail(e);
		}
	}
}
