package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class OptLocalCpsStoDecl implements IOptLocalCpsStoDecl {
  private OptLocalCpsStoDecl() {}

  public void print(String indent) {
    System.out.println(indent + "OptLocalCpsStoDecl");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
