package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingError;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingError;
import ch.fhnw.cpib.project.km.exceptions.ConstCheckingError;
import ch.fhnw.cpib.project.km.exceptions.InitCheckingError;

public interface ICommand {
	public String toString(String indent);

	public void addToEnvironment(Environment env, Context context);

	public void checkScope(Environment env) throws ScopeCheckingError;

	public void checkType(Environment env) throws TypeCheckingError;
	
	public void checkConst(Environment env) throws ConstCheckingError;
	
	public void checkInit(Environment env) throws InitCheckingError;
}
