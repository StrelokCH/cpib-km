package ch.fhnw.cpib.project.km.syntax.abst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.analysis.SymbolTable;
import ch.fhnw.cpib.project.km.exceptions.AliasingCheckingException;
import ch.fhnw.cpib.project.km.exceptions.CodeGenerationException;
import ch.fhnw.cpib.project.km.exceptions.ConstCheckingException;
import ch.fhnw.cpib.project.km.exceptions.FlowCheckingException;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingException;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingException;
import ch.fhnw.cpib.project.km.synthesis.CodeGenerationEnvironment;
import ch.fhnw.cpib.project.km.token.keywords.Const;
import ch.fhnw.cpib.project.km.token.keywords.FlowmodeIn;
import ch.fhnw.cpib.project.km.token.keywords.FlowmodeInOut;
import ch.fhnw.cpib.project.km.token.keywords.FlowmodeOut;
import ch.fhnw.cpib.project.km.token.keywords.MechmodeCopy;
import ch.fhnw.cpib.project.km.token.keywords.MechmodeReference;
import ch.fhnw.cpib.project.km.token.keywords.Type;
import ch.fhnw.cpib.project.km.token.keywords.Var;
import ch.fhnw.cpib.project.km.token.various.Identifier;
import ch.fhnw.cpib.project.km.vm.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.project.km.vm.IInstructions;

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

	public List<FullIdentifier> getParamList() {
		return paramList;
	}

	/**
	 * Returns a List of indices of the parameters in the order the caller must put
	 * them onto the stack.
	 * 
	 * @return
	 */
	public List<Integer> getParamOrder() {
		List<Integer> ret = new ArrayList<Integer>();
		// the ones that need a store first
		for (int i = 0; i < paramList.size(); i++) {
			if (needsStore(paramList.get(i))) {
				ret.add(i);
			}
		}
		// rest
		for (int i = 0; i < paramList.size(); i++) {
			if (!needsStore(paramList.get(i))) {
				ret.add(i);
			}
		}
		return ret;
	}

	/**
	 * Returns the number of stores that must follow a procedure call. This should
	 * return 0 if this is a function.
	 * 
	 * @return
	 */
	public int getStoreNumber() {
		int count = 0;
		for (FullIdentifier declaration : paramList) {
			if (needsStore(declaration)) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Return true, if the parameter must be stored after Procedure call.
	 * 
	 * @param declaration
	 * @return
	 */
	private boolean needsStore(FullIdentifier declaration) {
		return (declaration.getFlowmode() instanceof FlowmodeInOut || declaration.getFlowmode() instanceof FlowmodeOut)
				&& declaration.getMechmode() instanceof MechmodeCopy;

	}

	public List<FullIdentifier> getGlobImps() {
		return globImps;
	}

	public String toString(String indent) {
		String ret = indent + "(" + this.getClass().getSimpleName() + ", " + identifier.toString() + ",)\n";

		ret += indent + "    paramList";
		for (FullIdentifier progParam : paramList) {
			ret += progParam.toString(indent + "        ");
		}

		if (stoDecl != null) {
			ret += stoDecl.toString(indent + "   ");
		}

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
	public void addToEnvironment(Environment env, Context context) throws ScopeCheckingException {
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
			if (stoDecl != null) {
				symbolTable.addVariable(stoDecl.getFullIdentifier(), true);
			}
			for (FullIdentifier globImp : globImps) {
				// add type information if possible
				// note: in case no global store exists, checkScope throws an exception
				FullIdentifier temp = globImp;
				FullIdentifier declaration = context.symbolTable.getDeclaration(globImp);
				if (declaration != null) {
					temp = new FullIdentifier(globImp.getFlowmode(), new MechmodeReference(), globImp.getChangemode(),
							globImp.getIdentifier(), declaration.getType());
				}
				symbolTable.addVariable(temp, false);
			}
			for (StoDecl localStoDecl : cpsStoDecl) {
				symbolTable.addVariable(localStoDecl.getFullIdentifier(), true);
			}
		}

		for (ICommand command : cpsCmd) {
			command.addToEnvironment(env, newContext);
		}
	}

	@Override
	public void checkScope(Environment env) throws ScopeCheckingException {
		// check if globalImports really exist
		for (FullIdentifier globImp : globImps) {
			if (!env.rootContext.symbolTable.containsGlobal(globImp)) {
				throw new ScopeCheckingException("global import not found (" + globImp.getIdentifierName() + ").");
			}
		}

		for (ICommand command : cpsCmd) {
			command.checkScope(env);
		}
	}

	@Override
	public void checkType(Environment env) throws TypeCheckingException {
		for (ICommand command : cpsCmd) {
			command.checkType(env);
		}
	}

	@Override
	public void checkConst(Environment env) throws ConstCheckingException {
		for (ICommand command : cpsCmd) {
			command.checkConst(env);
		}
	}

	@Override
	public void checkFlow(Environment env) throws FlowCheckingException {
		for (FullIdentifier declaration : paramList) {
			if (!isProcedure() && !(declaration.getFlowmode() instanceof FlowmodeIn)) {
				// flowMode in functions must be InFlow
				throw new FlowCheckingException("Wrong flowmode of parameter " + declaration.toString("")
						+ " in function " + toString("") + " (must be FlowmodeIn).");
			}
			if (declaration.getFlowmode() instanceof FlowmodeIn
					&& declaration.getMechmode() instanceof MechmodeReference
					&& !(declaration.getChangemode() instanceof Const)) {
				// changeMode in routines for InFlow RefMech must be Const
				throw new FlowCheckingException("Wrong changemode of parameter " + declaration.toString("")
						+ " in routine " + toString("") + " (must be Const).");
			}
			if (declaration.getFlowmode() instanceof FlowmodeInOut && !(declaration.getChangemode() instanceof Var)) {
				// changeMode in routines for InoutFlow must be Var
				throw new FlowCheckingException("Wrong flowmode of parameter " + declaration.toString("")
						+ " in procedure " + toString("") + " (must be Var).");
			}
		}
	}

	@Override
	public void checkAliasing(Environment env) throws AliasingCheckingException {
		for (ICommand command : cpsCmd) {
			command.checkAliasing(env);
		}
	}

	@Override
	public void createCode(CodeGenerationEnvironment cgenv) throws CodeTooSmallError, CodeGenerationException {

		SymbolTable symbolTable = cgenv.env.contextMapping.get(this).symbolTable;

		// save location of parameters
		{
			int location = -1;
			List<Integer> reverseParamOrder = new ArrayList<>(getParamOrder());
			Collections.reverse(reverseParamOrder);
			for (int index : reverseParamOrder) {
				FullIdentifier declaration = paramList.get(index);

				symbolTable.setLocalVariablesLocation(declaration.getIdentifierName(), location);

				location--;
				if (needsStore(declaration)) {
					// needs two blocks
					location--;
				}
			}

			// save location of return
			if (stoDecl != null) {
				symbolTable.setLocalVariablesLocation(stoDecl.getFullIdentifier().getIdentifierName(), location);
			}
		}

		// save location of routine
		cgenv.addRoutine(this, cgenv.loc());

		// alloc local stores
		cgenv.code.put(cgenv.locInc(), new IInstructions.AllocBlock(cpsStoDecl.size()));

		// save location of local stores
		{
			int location = 3; // fp + 1 (ev) + 1 (pc) + 1
			for (StoDecl declaration : cpsStoDecl) {

				symbolTable.setLocalVariablesLocation(declaration.getFullIdentifier().getIdentifierName(), location);

				location++;
			}
		}

		// body
		for (ICommand command : cpsCmd) {
			command.createCode(cgenv);
		}

		// return
		cgenv.code.put(cgenv.locInc(), new IInstructions.Return(paramList.size() - getStoreNumber()));
	}
}
