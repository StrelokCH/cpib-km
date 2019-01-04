package ch.fhnw.cpib.project.km.syntax.abst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.exceptions.AliasingCheckingException;
import ch.fhnw.cpib.project.km.exceptions.ConstCheckingException;
import ch.fhnw.cpib.project.km.exceptions.InitCheckingException;
import ch.fhnw.cpib.project.km.exceptions.RoutineMatchException;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingException;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingException;
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
	public void checkScope(Environment env) throws ScopeCheckingException {
		// check if called procedure exists
		// throws exception in case no match
		RoutineDecl routineDecl = env.rootContext.symbolTable.findMatch(this);

		for (IExpression expr : parameters) {
			expr.checkScope(env);
		}

		for (Identifier identifier : identifiers) {
			Context context = env.contextMapping.get(this);
			FullIdentifier fullIdentifier = new FullIdentifier(identifier,null);
			if (!context.symbolTable.containsGlobal(fullIdentifier)) {
				throw new ScopeCheckingException("global identifier " + fullIdentifier.getIdentifierName() + " does not exist in current scope");
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
					throw new ScopeCheckingException("expression " + expression.toString("") + " should be an L-Value");
				}
				// check if non const is needed
				if (!param.isConst() && expression.isConst(env)) {
					throw new ScopeCheckingException("expression " + expression.toString("") + " must not be const");
				}
			}
		}
	}

	@Override
	public void checkType(Environment env) throws TypeCheckingException {
		// type checks of parameters were already made in checkScope
	}

	@Override
	public void checkConst(Environment env) throws ConstCheckingException {
		// const checking of parameters is performed in checkScope
	}

	@Override
	public void checkInit(Environment env) throws InitCheckingException {
		// Todo
	}

	@Override
	public void checkAliasing(Environment env) throws AliasingCheckingException {

		List<FullIdentifier> declParameters = new ArrayList<>();;
		try {
			RoutineDecl routineDecl = env.rootContext.symbolTable.findMatch(this);
			declParameters = routineDecl.getParamList();
		} catch (RoutineMatchException e) {
			throw new AliasingCheckingException("RoutineMatchException in checkAliasing. This exception should only occur in checkScope.");
		}
		
		// find out, which parameters can be used multiple times (R-Values)
		List<Integer> deleteParemeters = new ArrayList<>();
		for (int i = 0; i < declParameters.size(); i++) {
			if (!declParameters.get(i).needsLValue()) {
				deleteParemeters.add(i);
			}
		}
		
		Collections.reverse(deleteParemeters);
		List<IExpression> tempParameters = new ArrayList<>(parameters);
		for (Integer index : deleteParemeters) {
			// remove R-Values
			tempParameters.remove((int)index);
		}
		
		List<String> parameterNames = tempParameters.stream()
				.map(p -> ((StoreExpr)p).getIdentifier().getIdentifierName())
				.collect(Collectors.toList());

		// check if there are duplicates
		Set<String> duplicateFinder = new HashSet<>();
		for (String parameterName : parameterNames ) {
			if (!duplicateFinder.add(parameterName)) {
				throw new AliasingCheckingException("Parameter " + parameterName + " cannot be used multiple times in call " + this.toString(""));
			}
		}
	}
}
