package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

class TypedIdentIdent implements ITypedIdent {
  private final Identifier ident;
  private final Colon colon;
  private final Type atomtype;

  private TypedIdentIdent(final Identifier ident, final Colon colon, final Type atomtype) {
    this.ident = ident;
    this.colon = colon;
    this.atomtype = atomtype;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(ident + "    T " + ident.toString());
    System.out.println(ident + "    T " + colon.toString());
    System.out.println(ident + "    T " + atomtype.toString());
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
