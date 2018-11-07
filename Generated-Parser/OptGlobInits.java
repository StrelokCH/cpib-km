package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class OptGlobInits implements IOptGlobInits {
  private OptGlobInits() {}

  public void print(String indent) {
    System.out.println(indent + "OptGlobInits");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
