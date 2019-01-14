package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;
import ch.fhnw.cpib.project.km.syntax.abst.*;

import java.util.List;



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
	public ICommand toAbsSyn() {
		List<ICommand> list = repSemicolonCmd.toAbsSyn();
		list.add(0, cmd.toAbsSyn());
		return new CpsCmd(list);
	}
}
