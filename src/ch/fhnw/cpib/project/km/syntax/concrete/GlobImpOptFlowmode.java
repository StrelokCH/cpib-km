package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.syntax.abst.FullIdentifier;
import ch.fhnw.cpib.project.km.token.various.*;

public class GlobImpOptFlowmode implements IGlobImp {
	private final IOptFlowmode optFlowmode;
	private final IOptChangemode optChangemode;
	private final Identifier ident;

	public GlobImpOptFlowmode(final IOptFlowmode optFlowmode, final IOptChangemode optChangemode,
			final Identifier ident) {
		this.optFlowmode = optFlowmode;
		this.optChangemode = optChangemode;
		this.ident = ident;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		optFlowmode.print(indent + "    ");
		optChangemode.print(indent + "    ");
		System.out.println(indent + "    T " + ident.toString());
	}

	@Override
	public FullIdentifier toAbsSyn() {
		return new FullIdentifier(optFlowmode == null ? null : optFlowmode.toAbsSyn(), null, optChangemode == null ? null : optChangemode.toAbsSyn(), ident, null);
	}
}
