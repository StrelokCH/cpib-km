package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.token.various.Identifier;

public class ProcCallCmd implements ICommand {
	
	private final Identifier identifier;
	/* -- TO-DO: -- 
		routineCall
		[identifier]*/
	
	public ProcCallCmd(Identifier identifier) {
		super();
		this.identifier = identifier;
	}
	
	@Override
	public String toString(String indent) {
		return indent + "(" + this.getClass().getSimpleName() + ", " + identifier.toString() + ")\n";
	}
	
}
