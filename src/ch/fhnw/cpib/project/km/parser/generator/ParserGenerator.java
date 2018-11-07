package ch.fhnw.cpib.project.km.parser.generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;

public class ParserGenerator {

	private class Col {
		List<String> terminals;
		List<String> values = new ArrayList<>();

		Col(String terminal) {
			this.terminals = new ArrayList<>();
			this.terminals.add(terminal);
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
			System.out.println("file " + parseTablePath.toString() + " should be placed here: " + System.getProperty("user.dir"));
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

	public void generate(String outDir, String javaFileHeader) {
		new File(outDir).mkdirs();
		
		SummarizeSameRows();
		
		PrintWriter parserFile = createParserFile(outDir);
		for (Line l : parseTable) {
			String interfaceName = getInterfaceName(l.name);
			createInterface(outDir, interfaceName, javaFileHeader);
			addParserFunctionHeader(parserFile, l, interfaceName);
			for (Col c : l.cols) {
				String className = getPascalCase(l.name) + getPascalCase(c.values.get(0));
				createNonTerminalSymbolClass(outDir, className, interfaceName, javaFileHeader, c);
				addParserCol(parserFile, c, className);
			}
			addParserFunctionFooter(parserFile, l);
		}
		parserFile.close();
	}

	/**
	 * Summarize rows, if the production is the same
	 * Assumption: same productions with different terminals follow each others
	 */
	private void SummarizeSameRows() {
		for (Line l : parseTable) {
			Col previous = l.cols.get(0);
			for (int i = 1; i < l.cols.size(); i++) {
				Col current = l.cols.get(i);
				if (previous.values.equals(current.values)) {
					// same, add to previous and delete
					previous.terminals.addAll(current.terminals);
					l.cols.remove(i);
					i--;
				} else {
					// not same
					previous = current;
				}
			}
		}
	}

	private void createNonTerminalSymbolClass(String outDir, String className, String interfaceName, String insertPackage, Col c) {
		try {
			File f = new File(outDir + className + ".java");
			f.createNewFile();
			StringBuilder sb = new StringBuilder();
			addnl(sb, insertPackage);
			
			addnl(sb, "class " + className + " implements " + interfaceName);
			
			addnl(sb, "{");

			writeMembers(sb,c);
			writeConstructor(sb, className, c);
			//pw.println("IAbsSyn.IExpr toAbsSyn() { return null; }");
			writePrint(sb, className, c);
			writeToAbsSynDummy(sb);
			addnl(sb, "}");
			
			String rawSource = sb.toString();
			String formattedSource;
			
			try {
				formattedSource = new Formatter().formatSource(rawSource);
			} catch (FormatterException e) {
				System.out.println("Unable to format " + f.getName());
				formattedSource = rawSource;
			}
			PrintWriter pw = new PrintWriter(f);
			pw.print(formattedSource);

			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void writePrint(StringBuilder sb, String className, Col c) {
		addnl(sb, "public void print(String indent){");
		addnl(sb, "System.out.println(indent + \"" + className + "\");");
		for (final String v : c.values) {
			if (v.isEmpty()) {
				continue;
			}
			String name = getCamelCase(v);
			addnl(sb, name + ".print(indent + \"    \");");
		}
		addnl(sb, "}");
	}

	private void writeConstructor(StringBuilder sb, String className, Col c) {
		List<String> constrArgs = new LinkedList<>();
		addnl(sb, "private " + className + "(");
		for (final String v : c.values) {
			if (v.isEmpty()) {
				continue;
			}
			String name = getCamelCase(v);
			String type;
			if (IsTerminal(v)) {
				type = getPascalCase(v);
			} else {
				type = getInterfaceName(v);	
			}
			constrArgs.add("final " + type + " " + name);
		}
		addnl(sb, String.join(", ", constrArgs));
		addnl(sb, "){");
		for (final String v : c.values) {
			if (v.isEmpty()) {
				continue;
			}
			String name = getCamelCase(v);
			addnl(sb, "this." + name + " = " + name + ";");
		}
		addnl(sb, "}");
	}

	private void writeMembers(StringBuilder sb, Col c) {
		for (final String v : c.values) {
			if (v.isEmpty()) {
				continue;
			}
			String name = getCamelCase(v);
			String type;
			if (IsTerminal(v)) {
				type = getPascalCase(v);
			} else {
				type = getInterfaceName(v);	
			}
			sb.append("private final " + type + " " + name + ";");
		}
	}
	
	private void writeToAbsSynDummy(StringBuilder sb) {	
		addnl(sb, "@Override");
		addnl(sb, "public IAbsSyn.IExpr toAbsSyn() {");
		addnl(sb, "return null;");
		addnl(sb, "}");
	}

	private void addParserCol(PrintWriter parserFile, Col c, String className) {
		parserFile.println("if (" + GetIfExpression(c) +"){");
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

	private String GetIfExpression(Col c) {
		return "terminal instanceof " +c.terminals.stream().map(s -> getPascalCase(s)).collect(Collectors.joining(" || terminal instanceof "));
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
		if (name.isEmpty()) {
			return name;
		}
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
		
		if (name.equalsIgnoreCase("if")) {
			return "aIf";
		} else if (name.equalsIgnoreCase("else")) {
			return "aElse";
		} else if (name.equalsIgnoreCase("do")) {
			return "aDo";
		} else if (name.equalsIgnoreCase("while")) {
			return "aWhile";
		}
		
		return name;
	}
	
	private boolean IsTerminal(String s) {
		return !s.startsWith("<");
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

	private void createInterface(String outDir, String name, String javaFileHeader) {
		PrintWriter pw = null;
		try {
			File f = new File(outDir + name + ".java");
			f.createNewFile();
			pw = new PrintWriter(f);
			pw.println(javaFileHeader);

			pw.println("interface " + name);
			pw.println("{");
			pw.println("    IAbsSyn.IExpr toAbsSyn();");
			pw.println("    void print(String indent);");
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
	
	private void addnl(StringBuilder sb, String s) {
		sb.append(s);
		addnl(sb);
	}
	
	private void addnl(StringBuilder sb) {
		sb.append(System.getProperty("line.separator"));
	}

}
