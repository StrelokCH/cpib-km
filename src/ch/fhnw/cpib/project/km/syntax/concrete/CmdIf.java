package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.syntax.abst.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

import java.util.ArrayList;
import java.util.List;


public class CmdIf implements ICmd {
	private final If aIf;
	private final IExpr expr;
	private final Then then;
	private final ICpsCmd cpsCmd;
	private final Else aElse;
	private final ICpsCmd cpsCmd2;
	private final EndIf endif;

	public CmdIf(
			final If aIf,
			final IExpr expr,
			final Then then,
			final ICpsCmd cpsCmd,
			final Else aElse,
			final ICpsCmd cpsCmd2,
			final EndIf endif) {
		this.aIf = aIf;
		this.expr = expr;
		this.then = then;
		this.cpsCmd = cpsCmd;
		this.aElse = aElse;
		this.cpsCmd2 = cpsCmd2;
		this.endif = endif;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		System.out.println(indent + "    T " + aIf.toString());
		expr.print(indent + "    ");
		System.out.println(indent + "    T " + then.toString());
		cpsCmd.print(indent + "    ");
		System.out.println(indent + "    T " + aElse.toString());
		cpsCmd.print(indent + "    ");
		System.out.println(indent + "    T " + endif.toString());
	}

	@Override
	public ICommand toAbsSyn() {
		return new CondCmd(expr.toAbsSyn(), cpsCmd.toAbsSyn(), cpsCmd2.toAbsSyn());
	}
}
