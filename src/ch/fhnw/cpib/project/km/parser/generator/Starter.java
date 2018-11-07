package ch.fhnw.cpib.project.km.parser.generator;

public class Starter {
	public static void main(String [] args) {
		String parseTablePath = "Grammar_IML_ParseTable.txt";
		String outDir = "Generated-Parser/";
		String javaFileHeader = "package ch.fhnw.cpib.project.km.parser.generated;\r\n\r\n" + 
				"import ch.fhnw.cpib.project.km.token.*;";
		
		ParserGenerator g = new ParserGenerator(parseTablePath);
		g.generate(outDir, javaFileHeader);
	}
}
