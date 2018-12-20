package ch.fhnw.cpib.project.km.syntax.abst;

import java.util.List;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.analysis.SymbolTable;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingError;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingError;
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

	public Environment createEnvironment() throws ScopeCheckingError {
		Environment env = new Environment();
		
		// Create root symbolTable
		SymbolTable symbolTable = new SymbolTable(env);
		for (FullIdentifier progParam : progParamList) {
			// Program Parameters are handled as local variables
			symbolTable.addVariable(progParam,true);
		}
		for (IDecl decl : cpsDecl) {
			decl.appendSymbol(symbolTable, false);
		}
		
		Context rootContext = new Context(symbolTable);
		env.rootContext = rootContext;

		for (IDecl decl : cpsDecl) {
			decl.addToEnvironment(env, rootContext);
		}
		for (ICommand command : cpsCmd) {
			command.addToEnvironment(env, rootContext);
		}
		
		return env;
	}

	public void checkScope(Environment env) throws ScopeCheckingError {

		for (IDecl decl : cpsDecl) {
			decl.checkScope(env);
		}

		for (ICommand command : cpsCmd) {
			command.checkScope(env);
		}
	}

	public void checkType(Environment env) throws TypeCheckingError {

		for (IDecl decl : cpsDecl) {
			decl.checkType(env);
		}

		for (ICommand command : cpsCmd) {
			command.checkType(env);
		}
	}
}
