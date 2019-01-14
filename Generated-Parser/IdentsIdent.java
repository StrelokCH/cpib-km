package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class IdentsIdent implements IIdents {
  private final Ident ident;
  private final IRepCommaIdent repCommaIdent;

  private IdentsIdent(final Ident ident, final IRepCommaIdent repCommaIdent) {
    this.ident = ident;
    this.repCommaIdent = repCommaIdent;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(ident + "    T " + ident.toString());
    repCommaIdent.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
