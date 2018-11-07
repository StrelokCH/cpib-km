package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

class RepCommaParam implements IRepCommaParam {
  private RepCommaParam() {}

  public void print(String indent) {
    System.out.println(indent + "RepCommaParam");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
