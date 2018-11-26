package ch.fhnw.cpib.project.km.syntax.abst;

public class CpsCmd implements ICommand {

	private ICommand iComm;
		
	public CpsCmd(ICommand iComm) {
		super();
		this.iComm = iComm;
	}

	@Override
	public String toString(String indent) {
		return indent + "(" + this.getClass().getSimpleName() + ")\n"
				+ iComm.toString(indent + "    \n");
	}
}
