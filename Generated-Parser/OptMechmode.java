package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class OptMechmode implements IOptMechmode {
  private OptMechmode() {}

  public void print(String indent) {
    System.out.println(indent + "OptMechmode");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
