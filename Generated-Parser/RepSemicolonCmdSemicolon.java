package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class RepSemicolonCmdSemicolon implements IRepSemicolonCmd {
  private final Semicolon semicolon;
  private final ICpsCmd cpsCmd;

  private RepSemicolonCmdSemicolon(final Semicolon semicolon, final ICpsCmd cpsCmd) {
    this.semicolon = semicolon;
    this.cpsCmd = cpsCmd;
  }

  public void print(String indent) {
    System.out.println(indent + "RepSemicolonCmdSemicolon");
    semicolon.print(indent + "    ");
    cpsCmd.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
