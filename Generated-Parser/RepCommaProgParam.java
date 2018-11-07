package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class RepCommaProgParam implements IRepCommaProgParam {
  private RepCommaProgParam() {}

  public void print(String indent) {
    System.out.println(indent + "RepCommaProgParam");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
