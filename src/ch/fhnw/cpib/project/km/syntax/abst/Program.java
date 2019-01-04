package ch.fhnw.cpib.project.km.syntax.abst;

import java.util.List;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.analysis.SymbolTable;
import ch.fhnw.cpib.project.km.exceptions.ConstCheckingException;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingException;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingException;
import ch.fhnw.cpib.project.km.exceptions.AliasingCheckingException;
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

	public Environment createEnvironment() throws ScopeCheckingException {
		Environment env = new Environment();

		// Create root symbolTable
		SymbolTable symbolTable = new SymbolTable(env);
		for (FullIdentifier progParam : progParamList) {
			// Program Parameters are handled as local variables
			symbolTable.addVariable(progParam, true);
		}
		{
			// add predefined cast functions
			new RoutineDeclCastClamp().appendSymbol(symbolTable, false);
			new RoutineDeclCastCut().appendSymbol(symbolTable, false);
			new RoutineDeclCastCutUnsigned().appendSymbol(symbolTable, false);
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

	public void checkScope(Environment env) throws ScopeCheckingException {

		for (IDecl decl : cpsDecl) {
			decl.checkScope(env);
		}

		for (ICommand command : cpsCmd) {
			command.checkScope(env);
		}
	}

	public void checkType(Environment env) throws TypeCheckingException {

		for (IDecl decl : cpsDecl) {
			decl.checkType(env);
		}

		for (ICommand command : cpsCmd) {
			command.checkType(env);
		}
	}

	public void checkConst(Environment env) throws ConstCheckingException {
		for (IDecl decl : cpsDecl) {
			decl.checkConst(env);
		}

		for (ICommand command : cpsCmd) {
			command.checkConst(env);
		}
	}

	public void checkAliasing(Environment env) throws AliasingCheckingException {
		for (IDecl decl : cpsDecl) {
			decl.checkAliasing(env);
		}

		for (ICommand command : cpsCmd) {
			command.checkAliasing(env);
		}
	}
}
