package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class OptCpsExpr implements IOptCpsExpr {
  private OptCpsExpr() {}

  public void print(String indent) {
    System.out.println(indent + "OptCpsExpr");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
