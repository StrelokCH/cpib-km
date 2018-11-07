package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

class RepCommaIdent implements IRepCommaIdent {
  private RepCommaIdent() {}

  public void print(String indent) {
    System.out.println(indent + "RepCommaIdent");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
