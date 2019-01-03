package ch.fhnw.cpib.project.km.analysis;

import ch.fhnw.cpib.project.km.exceptions.ConstCheckingException;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingException;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingException;
import ch.fhnw.cpib.project.km.syntax.abst.Program;

public class StaticAnalyser {
	private final Program program;

	public StaticAnalyser(Program program) {
		this.program = program;
	}

	public boolean check() throws ScopeCheckingException, TypeCheckingException, ConstCheckingException {
		Environment env = program.createEnvironment();
		program.checkScope(env);
		program.checkType(env);
		program.checkConst(env);
		//return program.check();
		return true;
	}

}
