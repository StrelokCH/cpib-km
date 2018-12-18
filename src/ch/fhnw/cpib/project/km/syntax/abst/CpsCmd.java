package ch.fhnw.cpib.project.km.syntax.abst;

import java.util.List;

public class CpsCmd implements ICommand {

	private List<ICommand> iComm;
		
	public CpsCmd(List<ICommand> iComm) {
		super();
		this.iComm = iComm;
	}

	@Override
	public String toString(String indent) {
		String ret = indent + "(" + this.getClass().getSimpleName() + ")\n";	
		for (ICommand cmd : iComm) {
			ret += cmd.toString(indent + "    ");
		}
		return ret;
	}
}
