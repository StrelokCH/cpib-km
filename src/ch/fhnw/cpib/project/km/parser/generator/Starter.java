package ch.fhnw.cpib.project.km.parser.generator;

public class Starter {
	public static void main(String [] args) {
		String parseTablePath = "Grammar_IML_ParseTable.txt";
		String outDir = "output/";
		String insertPackage = "package ch.fhnw.cpib.km.parser;\n";
		
		ParserGenerator g = new ParserGenerator(parseTablePath);
		g.generate(outDir, insertPackage);
	}
}
