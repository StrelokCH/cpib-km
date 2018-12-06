package ch.fhnw.cpib.project.km.syntax.abst;

import java.util.List;

import ch.fhnw.cpib.project.km.token.various.Identifier;

public class Program {
	
	private final Identifier identifier;
	private final List<FullIdentifier> progParamList;
	private final List<IDecl> cpsDecl;
	private final List<ICommand> cpsCmd;

	public Program(Identifier identifier, List<FullIdentifier> progParamList, List<IDecl> cpsDecl,
			List<ICommand> cpsCmd) {
		super();
		this.identifier = identifier;
		this.progParamList = progParamList;
		this.cpsDecl = cpsDecl;
		this.cpsCmd = cpsCmd;
	}

	public String toString(String indent) {
		String ret = indent + "(" + this.getClass().getSimpleName() + ", " + identifier.toString() + ",)\n";
		for (FullIdentifier progParam : progParamList) {
			ret += progParam.toString(indent + "    ") + "\n";
		}
		for (IDecl decl : cpsDecl) {
			ret += decl.toString(indent + "    ") + "\n";
		}
		for (ICommand cmd : cpsCmd) {
			ret += cmd.toString(indent + "    ") + "\n";
		}
		return ret;
	}
}
