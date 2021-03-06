package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.syntax.abst.CpsCmd;
import ch.fhnw.cpib.project.km.syntax.abst.RoutineDecl;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class FunDeclFun implements IFunDecl {
	private final Function fun;
	private final Identifier ident;
	private final IParamList paramList;
	private final Returns returns;
	private final IStoDecl stoDecl;
	private final IGlobalGlobImps globalGlobImps;
	private final IOptLocalCpsStoDecl optLocalCpsStoDecl;
	private final Do aDo;
	private final ICpsCmd cpsCmd;
	private final EndFunction endfun;

	public FunDeclFun(final Function fun, final Identifier ident, final IParamList paramList, final Returns returns,
			final IStoDecl stoDecl, final IGlobalGlobImps globalGlobImps, final IOptLocalCpsStoDecl optLocalCpsStoDecl,
			final Do aDo, final ICpsCmd cpsCmd, final EndFunction endfun) {
		this.fun = fun;
		this.ident = ident;
		this.paramList = paramList;
		this.returns = returns;
		this.stoDecl = stoDecl;
		this.globalGlobImps = globalGlobImps;
		this.optLocalCpsStoDecl = optLocalCpsStoDecl;
		this.aDo = aDo;
		this.cpsCmd = cpsCmd;
		this.endfun = endfun;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		System.out.println(indent + "    T " + fun.toString());
		System.out.println(indent + "    T " + ident.toString());
		paramList.print(indent + "    ");
		System.out.println(indent + "    T " + returns.toString());
		stoDecl.print(indent + "    ");
		globalGlobImps.print(indent + "    ");
		optLocalCpsStoDecl.print(indent + "    ");
		System.out.println(indent + "    T " + aDo.toString());
		cpsCmd.print(indent + "    ");
		System.out.println(indent + "    T " + endfun.toString());
	}

	@Override
	public RoutineDecl toAbsSyn() {
		return new RoutineDecl(ident, paramList.toAbsSyn(), stoDecl.toAbsSyn(), globalGlobImps.toAbsSyn(),
				optLocalCpsStoDecl.toAbsSyn(), ((CpsCmd)cpsCmd.toAbsSyn()).getCommands());
	}
}
