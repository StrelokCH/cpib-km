package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class RepSemicolonCpsStoDecl implements IRepSemicolonCpsStoDecl {
  private RepSemicolonCpsStoDecl() {}

  public void print(String indent) {
    System.out.println(indent + "RepSemicolonCpsStoDecl");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
