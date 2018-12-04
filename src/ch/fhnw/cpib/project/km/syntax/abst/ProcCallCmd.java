package ch.fhnw.cpib.project.km.syntax.abst;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.cpib.project.km.token.various.Identifier;

public class ProcCallCmd implements ICommand {
	
	private final Identifier identifierProc;
	private final List<IExpression> parameters;
	private final List<Identifier> identifiers;
	

	
	public ProcCallCmd(Identifier identifierProc, List<IExpression> parameters, List<Identifier> identifiers) {
		super();
		this.identifierProc = identifierProc;
		this.parameters = parameters;
		this.identifiers = identifiers;
	}


	@Override
	public String toString(String indent) {
		return indent + "(" + this.getClass().getSimpleName() + ", " + identifierProc.toString() + ")\n";
		//To-Do show parameters and identifiers
	}
	
}
