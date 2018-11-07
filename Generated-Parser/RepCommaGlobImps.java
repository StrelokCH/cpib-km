package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class RepCommaGlobImps implements IRepCommaGlobImps {
  private RepCommaGlobImps() {}

  public void print(String indent) {
    System.out.println(indent + "RepCommaGlobImps");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
