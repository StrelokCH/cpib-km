package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.cpib.project.km.syntax.abst.IExpression;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;
import ch.fhnw.cpib.project.km.syntax.abst.*;

public class CmdWhile implements ICmd {
	private final While aWhile;
	private final IExpr expr;
	private final Do aDo;
	private final ICpsCmd cpsCmd;
	private final EndWhile endwhile;

	public CmdWhile(
			final While aWhile,
			final IExpr expr,
			final Do aDo,
			final ICpsCmd cpsCmd,
			final EndWhile endwhile) {
		this.aWhile = aWhile;
		this.expr = expr;
		this.aDo = aDo;
		this.cpsCmd = cpsCmd;
		this.endwhile = endwhile;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		System.out.println(indent + "    T " + aWhile.toString());
		expr.print(indent + "    ");
		System.out.println(indent + "    T " + aDo.toString());
		cpsCmd.print(indent + "    ");
		System.out.println(indent + "    T " + endwhile.toString());
	}

	@Override
	public ICommand toAbsSyn() {
		return new WhileCmd(expr.toAbsSyn(), cpsCmd.toAbsSyn());
	}
}
