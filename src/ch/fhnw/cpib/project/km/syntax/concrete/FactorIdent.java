package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class FactorIdent implements IFactor {
  private final Identifier ident;
  private final IIdent1 ident1;

  public FactorIdent(final Identifier ident, final IIdent1 ident1) {
    this.ident = ident;
    this.ident1 = ident1;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(ident + "    T " + ident.toString());
    ident1.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
