package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.token.keywords.Changemode;

public class StoDecl implements IDecl {
	private final FullIdentifier typedIdentifier;

	public StoDecl(FullIdentifier typedIdentifier) {
		this.typedIdentifier = typedIdentifier;
	}

	public String toString(String indent) {
		String ret = indent + "(" + this.getClass().getSimpleName() + ")\n";
		ret += typedIdentifier.toString(indent + "    ");
		return ret;
	}

}
