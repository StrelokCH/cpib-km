package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class OptChangemode implements IOptChangemode {
  private OptChangemode() {}

  public void print(String indent) {
    System.out.println(indent + "OptChangemode");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}