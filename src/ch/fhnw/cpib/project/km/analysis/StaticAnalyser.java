package ch.fhnw.cpib.project.km.analysis;

import ch.fhnw.cpib.project.km.exceptions.ContextCheckingException;
import ch.fhnw.cpib.project.km.syntax.abst.Program;

public class StaticAnalyser {
	private final Program program;

	public StaticAnalyser(Program program) {
		this.program = program;
	}

	public boolean check() throws ContextCheckingException {
		Environment env = program.createEnvironment();
		program.checkScope(env);
		program.checkType(env);
		program.checkConst(env);
		program.checkAliasing(env);
		// return program.check();
		return true;
	}

}
