package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class RepSemicolonCpsDecl implements IRepSemicolonCpsDecl {
  private RepSemicolonCpsDecl() {}

  public void print(String indent) {
    System.out.println(indent + "RepSemicolonCpsDecl");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
