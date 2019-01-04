package ch.fhnw.cpib.project.km.synthesis;

import ch.fhnw.cpib.project.km.analysis.StaticAnalyser;
import ch.fhnw.cpib.project.km.vm.CodeArray;
import ch.fhnw.cpib.project.km.vm.ICodeArray;

public class CodeGenerator {
	private final int codeArraySize = 1000;
	private final StaticAnalyser program;

	public CodeGenerator(StaticAnalyser program) {
		this.program = program;
		if (!program.isValid()) {
			throw new RuntimeException("the program must be valid to generate code");
		}
	}

	public ICodeArray generate() {
		CodeArray code = new CodeArray(codeArraySize);
		return code;
	}

}