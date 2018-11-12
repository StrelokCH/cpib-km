package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

class RepCommaIdentComma implements IRepCommaIdent {
  private final Comma comma;
  private final IIdents idents;

  private RepCommaIdentComma(final Comma comma, final IIdents idents) {
    this.comma = comma;
    this.idents = idents;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(indent + "    T " + comma.toString());
    idents.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
