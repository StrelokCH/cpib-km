package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class OptReloprTerm2 implements IOptReloprTerm2 {
  private OptReloprTerm2() {}

  public void print(String indent) {
    System.out.println(indent + "OptReloprTerm2");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
