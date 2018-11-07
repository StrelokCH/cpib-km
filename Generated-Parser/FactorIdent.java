package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class FactorIdent implements IFactor {
  private final Ident ident;
  private final IIdent1 ident1;

  private FactorIdent(final Ident ident, final IIdent1 ident1) {
    this.ident = ident;
    this.ident1 = ident1;
  }

  public void print(String indent) {
    System.out.println(indent + "FactorIdent");
    ident.print(indent + "    ");
    ident1.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
