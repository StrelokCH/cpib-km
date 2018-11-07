package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class RepSemicolonCmd implements IRepSemicolonCmd {
  private RepSemicolonCmd() {}

  public void print(String indent) {
    System.out.println(indent + "RepSemicolonCmd");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
