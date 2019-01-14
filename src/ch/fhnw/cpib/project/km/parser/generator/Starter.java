package ch.fhnw.cpib.project.km.parser.generator;

public class Starter {
	public static void main(String [] args) {
		String parseTablePath = "Grammar_IML_ParseTable.txt";
		String outDir = "Generated-Parser/";
		String javaFileHeader = "package ch.fhnw.cpib.project.km.syntax.concrete;\r\n\r\n" + 
				"import ch.fhnw.cpib.project.km.token.keywords.*;\r\n" +
				"import ch.fhnw.cpib.project.km.token.symbols.*;\r\n" +
				"import ch.fhnw.cpib.project.km.token.various.*;\r\n";
		
		ParserGenerator g = new ParserGenerator(parseTablePath);
		g.generate(outDir, javaFileHeader);
	}
}
