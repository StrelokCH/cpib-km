package ch.fhnw.cpib.km.parser.generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParserGenerator {

	private class Col {
		String terminal;
		List<String> values = new ArrayList<>();

		Col(String terminal) {
			this.terminal = terminal;
		}
	}

	private class Line {
		String name;
		List<Col> cols = new ArrayList<>();

		Line(String name) {
			this.name = name;
		}
	}

	List<Line> parseTable;

	public ParserGenerator(String parseTablePath) {
		parseTable = parse(parseTablePath);
	}

	private List<Line> parse(String parseTablePath) {
		List<String> lines = new ArrayList<>();
		try {
			Path filePath = new File(parseTablePath).toPath();
			Charset charset = Charset.defaultCharset();
			lines = Files.readAllLines(filePath, charset);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("file should be here: " + System.getProperty("user.dir"));
			e.printStackTrace();
		}
		List<Line> parseTable = new ArrayList<>();
		for (int l = 0; l < lines.size() - 2; l++) {
			String line = lines.get(l);
			Line currentLine = new Line(line);
			parseTable.add(currentLine);
			do {
				l++;
				line = lines.get(l);
				String terminal = getPascalCase(line.substring(11)); // " terminal "
				Col col = new Col(terminal);
				currentLine.cols.add(col);
				l++;
				line = lines.get(l);
				col.values.addAll(Arrays.asList(line.split("\\s+")));
				col.values.removeIf(v -> v.isEmpty());
				if (col.values.isEmpty()) {
					col.values.add("");
				}
			} while (l + 1 < lines.size() && lines.get(l + 1).startsWith("  "));
		}
		return parseTable;
	}

	public void generate(String outDir, String insertPackage) {
		new File(outDir).mkdirs();
		
		PrintWriter parserFile = createParserFile(outDir);
		for (Line l : parseTable) {
			String interfaceName = getInterfaceName(l.name);
			createInterface(outDir, interfaceName, insertPackage);
			addParserFunctionHeader(parserFile, l, interfaceName);
			for (Col c : l.cols) {
				String className = getPascalCase(l.name) + getPascalCase(c.terminal);
				createNonTerminalSymbolClass(outDir, className, interfaceName, insertPackage, c);
				addParserCol(parserFile, c, className);
			}
			addParserFunctionFooter(parserFile, l);
		}
		parserFile.close();
	}

	private void createNonTerminalSymbolClass(String outDir, String className, String interfaceName, String insertPackage, Col c) {
		PrintWriter pw = null;
		try {
			File f = new File(outDir + className + ".java");
			f.createNewFile();
			pw = new PrintWriter(f);

			pw.println(insertPackage);

			pw.println("class " + className + " implements " + interfaceName);
			pw.println("{");
			writeMembers(pw,c);
			writeConstructor(pw, className, c);
			//pw.println("IAbsSyn.IExpr toAbsSyn() { return null; }");
			writeToString(pw, className, c);
			pw.println("}");
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void writeToString(PrintWriter pw, String className, Col c) {
		pw.println("String toString(String indent){");
		pw.println("System.out.println(indent + \"" + className + "\");");
		for (final String v : c.values) {
			if (v.isEmpty()) {
				continue;
			}
			String name = getCamelCase(v);
			pw.println(name + ".toString(indent + \"    \");");
		}
		pw.println("}");
	}

	private void writeConstructor(PrintWriter pw, String className, Col c) {
		pw.println("private " + className + "(");
		for (final String v : c.values) {
			if (v.isEmpty()) {
				continue;
			}
			String name = getCamelCase(v);
			String type;
			if (v.startsWith("<")) {
				// non-terminal
				type = getInterfaceName(v);
			} else {
				// terminal
				type = getPascalCase(v);
			}
			pw.print("final " + type + " " + name);
			if (!v.equals(c.values.get(c.values.size()-1))) {
				pw.print(",");
			}
			pw.println();
		}
		pw.println("){");
		for (final String v : c.values) {
			if (v.isEmpty()) {
				continue;
			}
			String name = getCamelCase(v);
			pw.println("this." + name + " = " + name + ";");
		}
		pw.println("}");
	}

	private void writeMembers(PrintWriter pw, Col c) {
		for (final String v : c.values) {
			if (v.isEmpty()) {
				continue;
			}
			String name = getCamelCase(v);
			String type;
			if (v.startsWith("<")) {
				// non-terminal
				type = getInterfaceName(v);
			} else {
				// terminal
				type = getPascalCase(v);
			}
			pw.println("private final " + type + " " + name + ";");
		}
	}

	private void addParserCol(PrintWriter parserFile, Col c, String className) {
		parserFile.println("if (terminal instanceof " + c.terminal +"){");
		parserFile.println("System.out.println(\"" + String.join(" ", c.values) + "\\\"\");");
		for (final String v : c.values) {
			if (v.isEmpty()) {
				continue;
			}
			String name = getCamelCase(v);
			String type;
			String expr;
			if (v.startsWith("<")) {
				// non-terminal
				type = getInterfaceName(v);
				expr = getCamelCase(v) + "()";
			} else {
				// terminal
				type = getPascalCase(v);
				expr = "((" + type + ") consume(" + type + "))";
			}
			parserFile.println(type + " " + name + "=" + expr + ";");
		}
		parserFile.print("return new " + className + "(");
		for (final String v : c.values) {
			if (v.isEmpty()) {
				continue;
			}
			String name = getCamelCase(v);
			parserFile.print(name);
			if (!v.equals(c.values.get(c.values.size()-1))) {
				parserFile.print(",");
			}
		}
		parserFile.println(");");
		parserFile.println("}");	
	}

	private void addParserFunctionFooter(PrintWriter parserFile, Line l) {
		parserFile.println("throw new GrammarError(\"" + getPascalCase(l.name) + "\");");
		parserFile.println("}");
	}

	private void addParserFunctionHeader(PrintWriter parserFile, Line l, String interfaceName) {
		parserFile.println();
		parserFile.println("private " + interfaceName + " " + getPascalCase(l.name) + " throws GrammarError {");
		parserFile.println("System.out.print(\"\\\"" + getPascalCase(l.name) +" ::= \");");
	}
	
	private String getInterfaceName(String name) {
		return "I" + getPascalCase(name);
	}
	
	private String getPascalCase(String name) {
		name = getCamelCase(name);
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}
	
	private String getCamelCase(String name) {
		if (name.startsWith("<")) {
			// non-terminal
			name = name.substring(1,name.length()-1);
		} else {
			// terminal
			name = name.toLowerCase();
		}
		return name;
	}

	private PrintWriter createParserFile(String outDir) {
		PrintWriter pw = null;
		try {
			File f = new File(outDir + "parser.txt");
			f.createNewFile();
			pw = new PrintWriter(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pw;
	}

	private void createInterface(String outDir, String name, String insertPackage) {
		PrintWriter pw = null;
		try {
			File f = new File(outDir + name + ".java");
			f.createNewFile();
			pw = new PrintWriter(f);
			pw.println(insertPackage);

			pw.println("interface " + name);
			pw.println("{");
			pw.println("    IAbsSyn.IExpr toAbsSyn();");
			pw.println("    String toString(String indent);");
			pw.println("}");
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
