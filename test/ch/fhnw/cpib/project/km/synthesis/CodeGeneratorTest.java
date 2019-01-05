package ch.fhnw.cpib.project.km.synthesis;

import static org.junit.jupiter.api.Assertions.fail;

import ch.fhnw.cpib.project.km.analysis.StaticAnalyser;
import ch.fhnw.cpib.project.km.base.AbstractTest;
import ch.fhnw.cpib.project.km.exceptions.ContextCheckingException;
import ch.fhnw.cpib.project.km.exceptions.GrammarException;
import ch.fhnw.cpib.project.km.exceptions.LexicalErrorException;
import ch.fhnw.cpib.project.km.parser.Parser;
import ch.fhnw.cpib.project.km.scanner.Scanner;
import ch.fhnw.cpib.project.km.syntax.abst.Program;
import ch.fhnw.cpib.project.km.syntax.concrete.IProgram;
import ch.fhnw.cpib.project.km.token.ITokenList;

public class CodeGeneratorTest extends AbstractTest {
	@Override
	protected Object test(String input) throws Exception {
		try {
			ITokenList tokens = Scanner.scan(input);
			IProgram program = new Parser(tokens).parse();
			Program abstProgram = program.toAbsSyn();
			StaticAnalyser analyser = new StaticAnalyser(abstProgram);
			analyser.check();
			return new CodeGenerator(analyser).generate();
		} catch (LexicalErrorException e) {
			fail(e);
		} catch (GrammarException e) {
			fail(e);
		} catch (ContextCheckingException e) {
			fail(e);
		}
		return null;
	}
}
