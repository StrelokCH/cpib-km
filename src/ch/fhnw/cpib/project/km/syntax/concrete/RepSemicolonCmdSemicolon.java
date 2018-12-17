package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

import java.util.List;

import ch.fhnw.cpib.project.km.syntax.abst.IExpression;

public class RepSemicolonCmdSemicolon implements IRepSemicolonCmd {
	private final Semicolon semicolon;
	private final ICpsCmd cpsCmd;

	public RepSemicolonCmdSemicolon(final Semicolon semicolon, final ICpsCmd cpsCmd) {
		this.semicolon = semicolon;
		this.cpsCmd = cpsCmd;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		System.out.println(indent + "    T " + semicolon.toString());
		cpsCmd.print(indent + "    ");
	}

	@Override
	public List<IExpression> toAbsSyn() {
		return cpsCmd.toAbsSyn();
	}
}
