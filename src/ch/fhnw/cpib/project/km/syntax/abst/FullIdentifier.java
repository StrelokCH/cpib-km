package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.token.keywords.Changemode;
import ch.fhnw.cpib.project.km.token.keywords.Const;
import ch.fhnw.cpib.project.km.token.keywords.Flowmode;
import ch.fhnw.cpib.project.km.token.keywords.FlowmodeIn;
import ch.fhnw.cpib.project.km.token.keywords.FlowmodeInOut;
import ch.fhnw.cpib.project.km.token.keywords.FlowmodeOut;
import ch.fhnw.cpib.project.km.token.keywords.Mechmode;
import ch.fhnw.cpib.project.km.token.keywords.MechmodeCopy;
import ch.fhnw.cpib.project.km.token.keywords.MechmodeReference;
import ch.fhnw.cpib.project.km.token.keywords.Type;
import ch.fhnw.cpib.project.km.token.keywords.Var;
import ch.fhnw.cpib.project.km.token.various.Identifier;

public class FullIdentifier {

	public final Flowmode flowmode;
	public final Mechmode mechmode;
	public final Changemode changemode;
	private final Identifier identifier;
	private final Type type;

	public FullIdentifier(Flowmode flowmode, Mechmode mechmode, Changemode changemode, Identifier identifier,
			Type type) {
		this.flowmode = getFlowmode(flowmode,mechmode,changemode);
		this.mechmode = getMechmode(this.flowmode,mechmode,changemode);
		this.changemode = getChangemode(this.flowmode,this.mechmode,changemode);
		this.identifier = identifier;
		this.type = type;
	}
	
	public static Flowmode getFlowmode(Flowmode flowmode, Mechmode mechmode, Changemode changemode) {
		return flowmode == null ? new FlowmodeIn() : flowmode;
	}
	
	public static Mechmode getMechmode(Flowmode flowmode, Mechmode mechmode, Changemode changemode) {
		return mechmode == null ? new MechmodeCopy() : mechmode;
	}
	
	public static Changemode getChangemode(Flowmode flowmode, Mechmode mechmode, Changemode changemode) {
		if (changemode != null) {
			return changemode;
		}
		// default value depends on flowmode
		if (flowmode instanceof FlowmodeIn) {
			// for safety
			return new Const();
		} else {
			// must be var, otherwise out wouldn't make sense
			return new Var();
		}
	}

	public String toString(String indent) {
		String ret = indent + "(" + this.getClass().getSimpleName();
		ret += ", " + flowmode.toString();
		ret += ", " + mechmode.toString();
		ret += ", " + changemode.toString();
		ret += ", " + identifier.toString();
		ret += type == null ? "" : (", " + type.toString());
		ret += ")\n";
		return ret;
	}

	public String getIdentifierName() {
		return identifier.getIdentifier();
	}

	public Type getType() {
		return type;
	}

	/**
	 * Returns true if one of the following modes are present: FlowmodeOut,
	 * FlowmodeInOut, MechmodeReference
	 * 
	 * @return
	 */
	public boolean needsLValue() {
		return flowmode instanceof FlowmodeOut || flowmode instanceof FlowmodeInOut
				|| mechmode instanceof MechmodeReference;
	}
}
