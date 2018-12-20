package ch.fhnw.cpib.project.km.analysis;

import ch.fhnw.cpib.project.km.token.keywords.Int32;
import ch.fhnw.cpib.project.km.token.keywords.Int64;
import ch.fhnw.cpib.project.km.token.keywords.IntegerType;
import ch.fhnw.cpib.project.km.token.keywords.Type;

public class TypePromoter {
	/**
	 * Can Type from be promoted to Type to?
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public static boolean canPromote(Type from, Type to) {
		boolean isFromInt = from instanceof IntegerType;
		boolean isToInt = to instanceof IntegerType;
		if (isFromInt != isToInt) {
			// different types (bool vs int)
			return false;
		} else if (!isToInt) {
			// both bool
			return true;
		} else if (to instanceof Int64) {
			// every int type can be promoted to int64
			return true;
		} else if (from instanceof Int32) {
			// both are int32
			return true;
		}
		return false;
	}

	/**
	 * Promote one of the types so that both types are the same. Returns null if the
	 * types can not be promoted to match each other.
	 * 
	 * @param t1
	 * @param t2
	 * @return
	 */
	public static Type promote(Type t1, Type t2) {
		if (canPromote(t1, t2)) {
			// same or t1 < t2
			return t2;
		} else if (canPromote(t2, t1)) {
			// t2 < t1
			return t1;
		}
		return null;
	}
}
