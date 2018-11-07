package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

class Ident1 implements IIdent1 {
  private Ident1() {}

  public void print(String indent) {
    System.out.println(indent + "Ident1");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
