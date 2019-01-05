package ch.fhnw.cpib.project.km.synthesis;

import java.util.HashMap;
import java.util.Map;

import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.syntax.abst.RoutineDecl;
import ch.fhnw.cpib.project.km.vm.CodeArray;

public class CodeGenerationEnvironment {

	// various locations for code elements
	public final Map<String, Integer> globalStoreLocation = new HashMap<>();
	public final Map<RoutineDecl, Integer> procedureLocation = new HashMap<>();
	public final Map<RoutineDecl, Integer> functionLocation = new HashMap<>();

	public final Environment env;
	public final CodeArray code;

	private int loc = 0;

	public CodeGenerationEnvironment(Environment env, CodeArray code) {
		this.env = env;
		this.code = code;
	}

	/**
	 * returns the current position
	 * @return
	 */
	public int loc() {
		return loc;
	}

	/**
	 * returns the current position and increases loc afterwards
	 * @return
	 */
	public int locInc() {
		return loc++;
	}

	/**
	 * adds count to loc and returns the new loc
	 * @param count
	 * @return
	 */
	public int locAdd(int count) {
		loc += count;
		return loc;
	}

	public void addRoutine(RoutineDecl routine, int location) {
		if (routine.isProcedure()) {
			procedureLocation.put(routine,location);
		} else {
			functionLocation.put(routine,location);
		}
	}
}
