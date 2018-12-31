package ch.fhnw.cpib.project.km.syntax.abst;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.exceptions.ConstCheckingError;
import ch.fhnw.cpib.project.km.exceptions.InitCheckingError;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingError;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingError;
import ch.fhnw.cpib.project.km.token.keywords.FlowmodeInOut;
import ch.fhnw.cpib.project.km.token.keywords.FlowmodeOut;
import ch.fhnw.cpib.project.km.token.keywords.MechmodeReference;
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

	public Identifier getIdentifierProc() {
		return identifierProc;
	}

	public List<IExpression> getParameters() {
		return parameters;
	}

	public List<Identifier> getGlobImps() {
		return identifiers;
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

	@Override
	public void checkScope(Environment env) throws ScopeCheckingError {
		// check if called procedure exists
		// throws exception in case no match
		RoutineDecl routineDecl = env.rootContext.symbolTable.findMatch(this);
		
		for (IExpression expr : parameters) {
			expr.checkScope(env);
		}
		
		for (Identifier identifier : identifiers) {
			Context context = env.contextMapping.get(this);
			FullIdentifier fullIdentifier = new FullIdentifier(null, null, null, identifier,null);
			if (!context.symbolTable.containsGlobal(fullIdentifier)) {
				throw new ScopeCheckingError("global identifier " + fullIdentifier.getIdentifierName() + " does not exist in current scope");
			}
		}

		// Check for params that must be L-Values
		List<FullIdentifier> declParameters = routineDecl.getParamList();
		for (int i = 0; i < declParameters.size(); i++) {
			FullIdentifier param = declParameters.get(i);
			if (param.needsLValue()) {
				// must be an L-Value in call
				IExpression expression = parameters.get(i);
				if (!expression.isLValue()) {
					throw new ScopeCheckingError("expression " + expression.toString("") + " should be an L-Value");
				}
				// check if non const is needed
				if (!param.isConst() && expression.isConst(env)) {
					throw new ScopeCheckingError("expression " + expression.toString("") + " must not be const");
				}
			}
		}
	}

	@Override
	public void checkType(Environment env) throws TypeCheckingError {
		// type checks of parameters were already made in checkScope
	}

	@Override
	public void checkConst(Environment env) throws ConstCheckingError {
		// const checking of parameters is performed in checkScope
	}

	@Override
	public void checkInit(Environment env) throws InitCheckingError {
		//To-Do
	}
}
