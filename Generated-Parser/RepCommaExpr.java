package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

class RepCommaExpr implements IRepCommaExpr {
  private RepCommaExpr() {}

  public void print(String indent) {
    System.out.println(indent + "RepCommaExpr");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
