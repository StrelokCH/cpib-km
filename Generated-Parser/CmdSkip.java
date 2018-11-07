package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

class CmdSkip implements ICmd {
  private final Skip skip;

  private CmdSkip(final Skip skip) {
    this.skip = skip;
  }

  public void print(String indent) {
    System.out.println(indent + "CmdSkip");
    skip.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
