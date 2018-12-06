package ch.fhnw.cpib.project.km.syntax.concrete;

import java.util.List;

import ch.fhnw.cpib.project.km.syntax.abst.FullIdentifier;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class GlobImpsGlobImp implements IGlobImps {
	private final IGlobImp globImp;
	private final IRepCommaGlobImps repCommaGlobImps;

	public GlobImpsGlobImp(final IGlobImp globImp, final IRepCommaGlobImps repCommaGlobImps) {
		this.globImp = globImp;
		this.repCommaGlobImps = repCommaGlobImps;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		globImp.print(indent + "    ");
		repCommaGlobImps.print(indent + "    ");
	}

	@Override
	public List<FullIdentifier> toAbsSyn() {
		List<FullIdentifier> ret = repCommaGlobImps.toAbsSyn();
		ret.add(0, globImp.toAbsSyn());
		return ret;
	}
}
