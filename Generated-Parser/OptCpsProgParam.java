package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class OptCpsProgParam implements IOptCpsProgParam {
  private OptCpsProgParam() {}

  public void print(String indent) {
    System.out.println(indent + "OptCpsProgParam");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
