package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.exceptions.ConstCheckingError;
import ch.fhnw.cpib.project.km.exceptions.InitCheckingError;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingError;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingError;

public class SkipCmd implements ICommand {

	@Override
	public String toString(String indent) {
		return indent + "(" + this.getClass().getSimpleName() + ")\n";
	}

	@Override
	public void addToEnvironment(Environment env, Context context) {
		env.contextMapping.put(this, context);
	}

	@Override
	public void checkScope(Environment env) throws ScopeCheckingError {
		// not needed
	}

	@Override
	public void checkType(Environment env) throws TypeCheckingError {
		// not needed
	}

	@Override
	public void checkConst(Environment env) throws ConstCheckingError {
		// not needed
		
	}

	@Override
	public void checkInit(Environment env) throws InitCheckingError {
		//Not needed
	}
}
