package ch.fhnw.cpib.project.km.syntax.abst;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
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
		String ret = indent + "(" + this.getClass().getSimpleName() + ", " + identifierProc.toString() + ")\n";
		for (IExpression param : parameters) {
			ret += param.toString(indent + ", ");
		}
		for (Identifier ident : identifiers) {
			ret += ident + ", ";
		}
		ret += "\n";
		return ret;
	}

	@Override
	public void addToEnvironment(Environment env, Context context) {
		env.contextMapping.put(this, context);
		for (IExpression expr : parameters) {
			expr.addToEnvironment(env, context);
		}
	}
}
