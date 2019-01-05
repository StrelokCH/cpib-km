package ch.fhnw.cpib.project.km.synthesis;

import ch.fhnw.cpib.project.km.analysis.StaticAnalyser;
import ch.fhnw.cpib.project.km.exceptions.CodeGenerationException;
import ch.fhnw.cpib.project.km.vm.CodeArray;
import ch.fhnw.cpib.project.km.vm.ICodeArray;
import ch.fhnw.cpib.project.km.vm.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.project.km.vm.IInstructions;
import ch.fhnw.cpib.project.km.vm.IInstructions.IInstr;

public class CodeGenerator {
	private int codeArraySize = 1000;
	private final StaticAnalyser program;

	public CodeGenerator(StaticAnalyser program) {
		this.program = program;
		if (!program.isValid()) {
			throw new RuntimeException("the program must be valid to generate code");
		}
	}

	public ICodeArray generate() throws CodeGenerationException {
		CodeArray code = new CodeArray(codeArraySize);
		try {
			CodeGenerationEnvironment cgenv = new CodeGenerationEnvironment(program.getEnv(),code);
			program.getProgram().createCode(cgenv);
			// Second call after all elements have their fixed positions
			// (eg. routines,global stores).
			// Note: The guards inside createCode are omitted for code simplicity.
			//       The missing guards lead to poor performance as each instruction is written twice.
			program.getProgram().createCode(cgenv);
			code.put(cgenv.locInc(), new IInstructions.Stop());
		} catch (CodeTooSmallError e) {
			// try with bigger size
			codeArraySize *= 1.5;
			return generate();
		}
		return code;
	}

}