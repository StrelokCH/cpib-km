package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class OptGlobalCpsDecl implements IOptGlobalCpsDecl {
  private OptGlobalCpsDecl() {}

  public void print(String indent) {
    System.out.println(indent + "OptGlobalCpsDecl");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
