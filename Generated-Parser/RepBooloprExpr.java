package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class RepBooloprExpr implements IRepBooloprExpr {
  private RepBooloprExpr() {}

  public void print(String indent) {
    System.out.println(indent + "RepBooloprExpr");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
