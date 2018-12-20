package ch.fhnw.cpib.project.km.syntax.abst;

import java.util.List;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;

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
}
