package ch.fhnw.cpib.project.km.analysis;

import ch.fhnw.cpib.project.km.exceptions.ContextCheckingException;
import ch.fhnw.cpib.project.km.syntax.abst.Program;

public class StaticAnalyser {

	private final Program program;
	private Environment env;
	private boolean isValid = false;

	public StaticAnalyser(Program program) {
		this.program = program;
	}

	public boolean check() throws ContextCheckingException {
		env = program.createEnvironment();
		program.checkScope(env);
		program.checkType(env);
		program.checkConst(env);
		program.checkFlow(env);
		program.checkAliasing(env);
		isValid = true;
		return true;
	}

	public Program getProgram() {
		return program;
	}

	public Environment getEnv() {
		return env;
	}

	public boolean isValid() {
		return isValid;
	}
}
