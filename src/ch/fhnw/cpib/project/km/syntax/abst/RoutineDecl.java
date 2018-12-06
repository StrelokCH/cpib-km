package ch.fhnw.cpib.project.km.syntax.abst;

import java.util.List;

import ch.fhnw.cpib.project.km.token.keywords.Changemode;
import ch.fhnw.cpib.project.km.token.various.Identifier;

/**
 * May be a function or procedure declaration
 * @author Janik
 *
 */
public class RoutineDecl implements IDecl {

	private final Identifier identifier;
	private final List<FullIdentifier> paramList;
	private final StoDecl stoDecl; // is null if procedure
	private final List<FullIdentifier> globImps;
	private final List<StoDecl> cpsStoDecl;
	private final List<ICommand> cpsCmd;
	
	public RoutineDecl(Identifier identifier, List<FullIdentifier> paramList, StoDecl stoDecl,
			List<FullIdentifier> globImps, List<StoDecl> cpsStoDecl, List<ICommand> cpsCmd) {
		this.identifier = identifier;
		this.paramList = paramList;
		this.stoDecl = stoDecl;
		this.globImps = globImps;
		this.cpsStoDecl = cpsStoDecl;
		this.cpsCmd = cpsCmd;
	}

	public String toString(String indent) {
		String ret = indent + "(" + this.getClass().getSimpleName() + ", " + identifier.toString() + ",)\n";
		
		ret += indent + "    paramList";
		for (FullIdentifier progParam : paramList) {
			ret += progParam.toString(indent + "        ");
		}
		
		ret += stoDecl.toString(indent + "   ");
		
		ret += indent + "    globImps";
		for (FullIdentifier globImp : globImps) {
			ret += globImp.toString(indent + "        ");
		}
		
		ret += indent + "    cpsStoDecl";
		for (StoDecl stoDecl : cpsStoDecl) {
			ret += stoDecl.toString(indent + "        ");
		}
		
		ret += indent + "    cpsCmd";
		for (ICommand cmd : cpsCmd) {
			ret += cmd.toString(indent + "        ");
		}
		return ret;
	}

}
