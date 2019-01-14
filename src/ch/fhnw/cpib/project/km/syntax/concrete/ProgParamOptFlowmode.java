package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.syntax.abst.FullIdentifier;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class ProgParamOptFlowmode implements IProgParam {
	private final IOptFlowmode optFlowmode;
	private final IOptChangemode optChangemode;
	private final ITypedIdent typedIdent;

	public ProgParamOptFlowmode(final IOptFlowmode optFlowmode, final IOptChangemode optChangemode,
			final ITypedIdent typedIdent) {
		this.optFlowmode = optFlowmode;
		this.optChangemode = optChangemode;
		this.typedIdent = typedIdent;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		optFlowmode.print(indent + "    ");
		optChangemode.print(indent + "    ");
		typedIdent.print(indent + "    ");
	}

	@Override
	public FullIdentifier toAbsSyn() {
		return typedIdent.toAbsSyn(optFlowmode.toAbsSyn(), null, optChangemode.toAbsSyn());
	}
}
