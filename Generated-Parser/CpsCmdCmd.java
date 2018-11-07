package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class CpsCmdCmd implements ICpsCmd {
  private final ICmd cmd;
  private final IRepSemicolonCmd repSemicolonCmd;

  private CpsCmdCmd(final ICmd cmd, final IRepSemicolonCmd repSemicolonCmd) {
    this.cmd = cmd;
    this.repSemicolonCmd = repSemicolonCmd;
  }

  public void print(String indent) {
    System.out.println(indent + "CpsCmdCmd");
    cmd.print(indent + "    ");
    repSemicolonCmd.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
