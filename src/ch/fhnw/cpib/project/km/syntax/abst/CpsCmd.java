package ch.fhnw.cpib.project.km.syntax.abst;

import java.util.List;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.exceptions.AliasingCheckingException;
import ch.fhnw.cpib.project.km.exceptions.ConstCheckingException;
import ch.fhnw.cpib.project.km.exceptions.InitCheckingException;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingException;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingException;

public class CpsCmd implements ICommand {

	private List<ICommand> commands;
		
	public CpsCmd(List<ICommand> commands) {
		super();
		this.commands = commands;
	}
	
	public List<ICommand> getCommands(){
		return commands;
	}

	@Override
	public String toString(String indent) {
		String ret = indent + "(" + this.getClass().getSimpleName() + ")\n";	
		for (ICommand cmd : commands) {
			ret += cmd.toString(indent + "    ");
		}
		return ret;
	}

	@Override
	public void addToEnvironment(Environment env, Context context) {
		env.contextMapping.put(this, context);
		for (ICommand command : commands) {
			command.addToEnvironment(env, context);
		}
	}

	@Override
	public void checkScope(Environment env) throws ScopeCheckingException {
		for (ICommand command : commands) {
			command.checkScope(env);
		}
	}

	@Override
	public void checkType(Environment env) throws TypeCheckingException {
		for (ICommand command : commands) {
			command.checkType(env);
		}
	}

	@Override
	public void checkConst(Environment env) throws ConstCheckingException {
		for (ICommand command : commands) {
			command.checkConst(env);
		}
	}

	@Override
	public void checkInit(Environment env) throws InitCheckingException {
		for (ICommand command : commands) {
			command.checkInit(env);
		}
	}

	@Override
	public void checkAliasing(Environment env) throws AliasingCheckingException {
		for (ICommand command : commands) {
			command.checkAliasing(env);
		}
	}
}
