package ch.fhnw.cpib.project.km.analysis;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.cpib.project.km.exceptions.RoutineMatchException;
import ch.fhnw.cpib.project.km.exceptions.TypeCheckingException;
import ch.fhnw.cpib.project.km.syntax.abst.FullIdentifier;
import ch.fhnw.cpib.project.km.syntax.abst.FunctionCallExpr;
import ch.fhnw.cpib.project.km.syntax.abst.IExpression;
import ch.fhnw.cpib.project.km.syntax.abst.ProcCallCmd;
import ch.fhnw.cpib.project.km.syntax.abst.RoutineDecl;
import ch.fhnw.cpib.project.km.token.keywords.Type;
import ch.fhnw.cpib.project.km.token.various.Identifier;

public class RoutineMatcher {

	private final Environment env;

	public RoutineMatcher(Environment env) {
		this.env = env;
	}

	private static class RoutineCall {
		Identifier routineName;
		List<Type> parameterTypes;
		List<Boolean> parameterLValue;
		List<Identifier> globImps;
	}

	public RoutineDecl findMatch(List<RoutineDecl> procedures, ProcCallCmd procCallCmd) throws RoutineMatchException {
		RoutineCall call = new RoutineCall();
		call.routineName = procCallCmd.getIdentifierProc();
		try {
			call.parameterTypes = new ArrayList<>();
			call.parameterLValue = new ArrayList<>();
			for (IExpression expr : procCallCmd.getParameters()) {
				call.parameterTypes.add(expr.checkType(env));
				call.parameterLValue.add(expr.isLValue());
			}
		} catch (TypeCheckingException e) {
			throw new RoutineMatchException(
					"Type error while trying to match " + procCallCmd.toString("") + ". Message=" + e.getMessage());
		}
		call.globImps = procCallCmd.getGlobImps();

		return findMatch(procedures, call);
	}

	public RoutineDecl findMatch(List<RoutineDecl> functions, FunctionCallExpr functionCallExpr)
			throws RoutineMatchException {
		RoutineCall call = new RoutineCall();
		call.routineName = functionCallExpr.getIdentifier();
		try {
			call.parameterTypes = new ArrayList<>();
			call.parameterLValue = new ArrayList<>();
			for (IExpression expr : functionCallExpr.getParameters()) {
				call.parameterTypes.add(expr.checkType(env));
				call.parameterLValue.add(expr.isLValue());
			}
		} catch (TypeCheckingException e) {
			throw new RoutineMatchException("Type error while trying to match " + functionCallExpr.toString("")
					+ ". Message=" + e.getMessage());
		}
		call.globImps = new ArrayList<>();

		return findMatch(functions, call);
	}

	private RoutineDecl findMatch(List<RoutineDecl> routines, RoutineCall call) throws RoutineMatchException {
		List<RoutineDecl> candidates = new ArrayList<>(routines);

		// Filter by name
		candidates.removeIf(c -> !c.getIdentifier().getIdentifier().equals(call.routineName.getIdentifier()));

		if (candidates.isEmpty()) {
			throw new RoutineMatchException("Routine with identifier " + call.routineName.getIdentifier() + " not found.");
		}

		// Filter by number of parameters
		candidates.removeIf(c -> c.getParamList().size() != call.parameterTypes.size());

		if (candidates.isEmpty()) {
			throw new RoutineMatchException("No routine with identifier " + call.routineName.getIdentifier()
					+ " found which has " + call.parameterTypes.size() + " parameters.");
		}

		// Filter by parameters
		candidates.removeIf(c -> {
			for (int i = 0; i < 0; i++) {
				FullIdentifier param = c.getParamList().get(i);
				Type actualType = call.parameterTypes.get(i);
				Type expectedType = param.getType();
				if (param.needsLValue()) {
					// Must be L-Value which means the type must match exactly
					if (!TypePromoter.isSameType(actualType, expectedType)) {
						// reference -> must be same type
						return true;
					}
					// Check if it is a L-Value
					if (!call.parameterLValue.get(i)) {
						// is an R-Value
						return true;
					}
				} else {
					// Can be a R-Value which means a implicit cast is ok
					if (!TypePromoter.canPromote(actualType, expectedType)) {
						return true;
					}
				}
			}
			// all matching
			return false;
		});

		if (candidates.isEmpty()) {
			throw new RoutineMatchException("No routine with identifier " + call.routineName.getIdentifier()
					+ " found that has the matching " + call.parameterTypes.size() + " parameters.");
		}

		// check for ambiguity
		if (candidates.size() > 1) {

			// find perfect match
			List<RoutineDecl> perfectCandidates = new ArrayList<>(candidates);
			perfectCandidates.removeIf(c -> {
				for (int i = 0; i < 0; i++) {
					FullIdentifier param = c.getParamList().get(i);
					Type actualType = call.parameterTypes.get(i);
					Type expectedType = param.getType();
					// the type must match exactly
					if (!TypePromoter.isSameType(actualType, expectedType)) {
						return true;
					}
				}
				// all perfectly matching
				return false;
			});
			
			if (perfectCandidates.size() != 1) {
				// no single perfect match
				throw new RoutineMatchException("Multiple matching routines with identifier " + call.routineName.getIdentifier()
						+ " found. First match is " + candidates.get(0) + ". There are " + candidates.size() + " matches.");
			} else {
				candidates = perfectCandidates;
			}
		}

		// success! found one single matching routine
		return candidates.get(0);
	}

}
