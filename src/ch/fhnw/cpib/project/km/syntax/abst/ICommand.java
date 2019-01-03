package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingException;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingException;
import ch.fhnw.cpib.project.km.exceptions.ConstCheckingException;
import ch.fhnw.cpib.project.km.exceptions.InitCheckingException;

public interface ICommand {
	public String toString(String indent);

	public void addToEnvironment(Environment env, Context context);

	public void checkScope(Environment env) throws ScopeCheckingException;

	public void checkType(Environment env) throws TypeCheckingException;
	
	public void checkConst(Environment env) throws ConstCheckingException;
	
	public void checkInit(Environment env) throws InitCheckingException;
}
