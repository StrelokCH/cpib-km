package ch.fhnw.cpib.project.km.syntax.abst;

import java.util.List;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.exceptions.RoutineMatchError;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingError;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingError;
import ch.fhnw.cpib.project.km.token.keywords.FlowmodeInOut;
import ch.fhnw.cpib.project.km.token.keywords.FlowmodeOut;
import ch.fhnw.cpib.project.km.token.keywords.MechmodeReference;
import ch.fhnw.cpib.project.km.token.keywords.Type;
import ch.fhnw.cpib.project.km.token.various.Identifier;

public class FunctionCallExpr implements IExpression {
	private final Identifier identifier;
	private final List<IExpression> expressions;

	public FunctionCallExpr(Identifier identifier, List<IExpression> expressions) {
		this.identifier = identifier;
		this.expressions = expressions;
	}

	@Override
	public String toString(String indent) {
		String ret = indent + "(" + this.getClass().getSimpleName() + ", " + identifier.toString() + ")\n";
		for (IExpression expr : expressions) {
			ret += expr.toString(indent + "    ") + "\n";
		}
		return ret;
	}

	@Override
	public void addToEnvironment(Environment env, Context context) {
		env.contextMapping.put(this, context);
		for (IExpression expression : expressions) {
			expression.addToEnvironment(env, context);
		}
	}

	@Override
	public void checkScope(Environment env) throws ScopeCheckingError {
		// check if called function exists
		// throws exception in case no match
		RoutineDecl routineDecl = env.rootContext.symbolTable.findMatch(this);

		for (IExpression expression : expressions) {
			expression.checkScope(env);
		}

		// Check for params that must be L-Values
		List<FullIdentifier> parameters = routineDecl.getParamList();
		for (int i = 0; i < parameters.size(); i++) {
			FullIdentifier param = parameters.get(i);
			if (param.needsLValue()) {
				// must be an L-Value in call
				IExpression expression = expressions.get(i);
				if (!expression.isLValue()) {
					throw new ScopeCheckingError("expression " + expression.toString("") + "should be an L-Value");
				}
			}
		}
	}

	@Override
	public Type checkType(Environment env) throws TypeCheckingError {
		// type checks of parameters were already made in checkScope
		try {
			RoutineDecl routineDecl = env.rootContext.symbolTable.findMatch(this);
			return routineDecl.getReturnType();
		} catch (RoutineMatchError e) {
			throw new RuntimeException("RoutineMatchError in FunctionCallExpr.checkType.");
		}
	}
}
