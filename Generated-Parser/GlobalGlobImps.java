package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class GlobalGlobImps implements IGlobalGlobImps {
  private GlobalGlobImps() {}

  public void print(String indent) {
    System.out.println(indent + "GlobalGlobImps");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}