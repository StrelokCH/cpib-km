package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

import java.util.List;

import ch.fhnw.cpib.project.km.syntax.abst.IExpression;

public class CpsCmdCmd implements ICpsCmd {
	private final ICmd cmd;
	private final IRepSemicolonCmd repSemicolonCmd;

	public CpsCmdCmd(final ICmd cmd, final IRepSemicolonCmd repSemicolonCmd) {
		this.cmd = cmd;
		this.repSemicolonCmd = repSemicolonCmd;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		cmd.print(indent + "    ");
		repSemicolonCmd.print(indent + "    ");
	}

	@Override
	public List<IExpression> toAbsSyn() {
		List<IExpression> l = cmd.toAbsSyn();
		l.addAll(repSemicolonCmd.toAbsSyn());

		return l;
	}
}
