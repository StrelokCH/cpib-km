package ch.fhnw.cpib.project.km.analysis;

import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingError;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingError;
import ch.fhnw.cpib.project.km.syntax.abst.Program;

public class StaticAnalyser {
	private final Program program;

	public StaticAnalyser(Program program) {
		this.program = program;
	}

	public boolean check() throws ScopeCheckingError, TypeCheckingError {
		Environment env = program.createEnvironment();
		program.checkScope(env);
		program.checkType(env);
		//return program.check();
		return true;
	}

}
