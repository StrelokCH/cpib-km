package ch.fhnw.cpib.project.km.syntax.abst;

import java.sql.Blob;
import java.util.List;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.analysis.SymbolTable;
import ch.fhnw.cpib.project.km.exceptions.ConstCheckingError;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingError;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingError;
import ch.fhnw.cpib.project.km.token.keywords.Type;
import ch.fhnw.cpib.project.km.token.various.Identifier;

/**
 * May be a function or procedure declaration
 * 
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

	public boolean isProcedure() {
		return stoDecl == null;
	}
	
	public Type getReturnType() {
		if (isProcedure()) {
			// procedures don't have a return value
			return null;
		}
		return stoDecl.getFullIdentifier().getType();
	}
	
	public Identifier getIdentifier() {
		return identifier;
	}
	
	public List<FullIdentifier> getParamList(){
		return paramList;
	}
	
	public List<FullIdentifier> getGlobImps(){
		return globImps;
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

	@Override
	public void appendSymbol(SymbolTable symbolTable, boolean localScope) {
		symbolTable.addRoutine(this);
	}

	@Override
	public void addToEnvironment(Environment env, Context context) throws ScopeCheckingError {
		// create new context for this routine
		Context newContext = context.clone();
		SymbolTable symbolTable = newContext.symbolTable;
		symbolTable.clearVariables();
		env.contextMapping.put(this, newContext);

		// add local variables & imports
		{
			for (FullIdentifier param : paramList) {
				symbolTable.addVariable(param, true);
			}
			symbolTable.addVariable(stoDecl.getFullIdentifier(), true);
			for (FullIdentifier globImp : globImps) {
				symbolTable.addVariable(globImp, false);
			}
			for (StoDecl localStoDecl : cpsStoDecl) {
				symbolTable.addVariable(localStoDecl.getFullIdentifier(), true);
			}
		}
		
		for (ICommand command : cpsCmd) {
			command.addToEnvironment(env,newContext);
		}
	}

	@Override
	public void checkScope(Environment env) throws ScopeCheckingError {
		// check if globalImports really exist
		for (FullIdentifier globImp : globImps) {
			if (!env.rootContext.symbolTable.containsGlobal(globImp)) {
				throw new ScopeCheckingError("global import not found (" + globImp.getIdentifierName()+ ")");
			}
		}
		
		for (ICommand command : cpsCmd) {
			command.checkScope(env);
		}
	}

	@Override
	public void checkType(Environment env) throws TypeCheckingError {
		for (ICommand command : cpsCmd) {
			command.checkType(env);
		}
	}

	@Override
	public void checkConst(Environment env) throws ConstCheckingError {
		for (ICommand command : cpsCmd) {
			command.checkConst(env);
		}
	}

	public void checkFlow(Environment env) {
		// Todo
		// flowMode in functions must be InFlow
		// changeMode in routines for InFlow RefMech must be Const
		// changeMode in routines for InoutFlow must be Var
	}
}
