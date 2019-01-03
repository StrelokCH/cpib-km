package ch.fhnw.cpib.project.km.syntax.abst;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import ch.fhnw.cpib.project.km.analysis.StaticAnalyser;
import ch.fhnw.cpib.project.km.base.AbstractTest;
import ch.fhnw.cpib.project.km.code.IMLTestPrograms;
import ch.fhnw.cpib.project.km.exceptions.GrammarException;
import ch.fhnw.cpib.project.km.exceptions.LexicalErrorException;
import ch.fhnw.cpib.project.km.parser.Parser;
import ch.fhnw.cpib.project.km.scanner.Scanner;
import ch.fhnw.cpib.project.km.syntax.concrete.IProgram;
import ch.fhnw.cpib.project.km.token.ITokenList;

public class AbstractSyntaxTest extends AbstractTest {
	@Override
	protected Object test(String input) throws Exception {
		try {
			ITokenList tokens = Scanner.scan(input);
			IProgram program = new Parser(tokens).parse();
			Program abstProgram = program.toAbsSyn();
			System.out.println(abstProgram.toString(""));
			return abstProgram;
		} catch (LexicalErrorException e) {
			fail(e);
		} catch (GrammarException e) {
			fail(e);
		}
		return null;
	}
}
