package ch.fhnw.cpib.project.km.syntax.abst;

import java.util.List;
import java.util.stream.Collectors;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.analysis.SymbolTable;
import ch.fhnw.cpib.project.km.exceptions.ConstCheckingException;
import ch.fhnw.cpib.project.km.exceptions.FlowCheckingException;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingException;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingException;
import ch.fhnw.cpib.project.km.synthesis.CodeGenerationEnvironment;
import ch.fhnw.cpib.project.km.exceptions.AliasingCheckingException;
import ch.fhnw.cpib.project.km.exceptions.CodeGenerationException;
import ch.fhnw.cpib.project.km.token.keywords.FlowmodeIn;
import ch.fhnw.cpib.project.km.token.keywords.FlowmodeOut;
import ch.fhnw.cpib.project.km.token.various.Identifier;
import ch.fhnw.cpib.project.km.vm.CodeArray;
import ch.fhnw.cpib.project.km.vm.IInstructions;
import ch.fhnw.cpib.project.km.vm.ICodeArray.CodeTooSmallError;

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
			new RoutineDeclCastLossless().appendSymbol(symbolTable, false);
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

	public void checkFlow(Environment env) throws FlowCheckingException {
		// Todo
		// is only implemented for IDecl yet
		for (IDecl decl : cpsDecl) {
			decl.checkFlow(env);
		}
	}

	public void createCode(CodeGenerationEnvironment cgenv) throws CodeTooSmallError, CodeGenerationException {

		// Prog Params
		{
			for (int i = 0; i < progParamList.size(); i++) {
				// safe position of progParams
				cgenv.globalStoreLocation.put(progParamList.get(i).getIdentifierName(), i);
			}
			for (FullIdentifier progParam : progParamList) {
				// input progParams
				if (!(progParam.getFlowmode() instanceof FlowmodeOut)) {
					InputCmd.createCode(cgenv, progParam.getType(), progParam.getIdentifierName());
				}
			}
		}

		// stores
		{
			int startLocation = progParamList.size();
			List<IDecl> stores = cpsDecl.stream().filter(d -> d instanceof StoDecl).collect(Collectors.toList());
			for (int i = 0; i < stores.size(); i++) {
				// save location
				StoDecl store = (StoDecl) stores.get(i);
				cgenv.globalStoreLocation.put(store.getFullIdentifier().getIdentifierName(), startLocation + i);

				stores.get(i).createCode(cgenv);
			}
		}

		// commands
		for (ICommand command : cpsCmd) {
			command.createCode(cgenv);
		}

		// Output prog Params
		{
			for (FullIdentifier progParam : progParamList) {
				if (!(progParam.getFlowmode() instanceof FlowmodeIn)) {
					// load prog param to stack
					cgenv.code.put(cgenv.locInc(),
							new IInstructions.LoadImInt(cgenv.globalStoreLocation.get(progParam.getIdentifierName())));
					// output
					OutputCmd.createCode(cgenv, progParam.getType(), progParam.getIdentifierName());
				}
			}
		}

		// routines
		{
			List<IDecl> routines = cpsDecl.stream().filter(d -> d instanceof RoutineDecl).collect(Collectors.toList());
			for (IDecl routine : routines) {
				routine.createCode(cgenv);
			}
		}
	}
}
