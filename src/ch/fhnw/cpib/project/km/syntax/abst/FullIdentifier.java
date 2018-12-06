package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.token.keywords.Changemode;
import ch.fhnw.cpib.project.km.token.keywords.Flowmode;
import ch.fhnw.cpib.project.km.token.keywords.Mechmode;
import ch.fhnw.cpib.project.km.token.keywords.Type;
import ch.fhnw.cpib.project.km.token.various.Identifier;

public class FullIdentifier {

	private final Flowmode flowmode;
	private final Mechmode mechmode;
	private final Changemode changemode;
	private final Identifier identifier;
	private final Type type;

	public FullIdentifier(Flowmode flowmode, Mechmode mechmode, Changemode changemode, Identifier identifier,
			Type type) {
		this.flowmode = flowmode;
		this.mechmode = mechmode;
		this.changemode = changemode;
		this.identifier = identifier;
		this.type = type;
	}

	public String toString(String indent) {
		String ret = indent + "(" + this.getClass().getSimpleName();
		ret += flowmode == null ? "" : (", " + flowmode.toString());
		ret += mechmode == null ? "" : (", " + mechmode.toString());
		ret += changemode == null ? "" : (", " + changemode.toString());
		ret += ", " + identifier.toString();
		ret += type == null ? "" : (", " + type.toString());
		ret += ")\n";
		return ret;
	}
}
