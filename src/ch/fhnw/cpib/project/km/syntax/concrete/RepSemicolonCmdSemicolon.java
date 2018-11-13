package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class RepSemicolonCmdSemicolon implements IRepSemicolonCmd {
  private final Semicolon semicolon;
  private final ICpsCmd cpsCmd;

  private RepSemicolonCmdSemicolon(final Semicolon semicolon, final ICpsCmd cpsCmd) {
    this.semicolon = semicolon;
    this.cpsCmd = cpsCmd;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(indent + "    T " + semicolon.toString());
    cpsCmd.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
