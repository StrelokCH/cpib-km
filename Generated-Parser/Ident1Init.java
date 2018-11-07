package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class Ident1Init implements IIdent1 {
  private final Init init;

  private Ident1Init(final Init init) {
    this.init = init;
  }

  public void print(String indent) {
    System.out.println(indent + "Ident1Init");
    init.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
