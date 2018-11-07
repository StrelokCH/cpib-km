package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class OptCpsParam implements IOptCpsParam {
  private OptCpsParam() {}

  public void print(String indent) {
    System.out.println(indent + "OptCpsParam");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
