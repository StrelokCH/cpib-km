package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

class TypedIdentIdent implements ITypedIdent {
  private final Ident ident;
  private final Colon colon;
  private final Atomtype atomtype;

  private TypedIdentIdent(final Ident ident, final Colon colon, final Atomtype atomtype) {
    this.ident = ident;
    this.colon = colon;
    this.atomtype = atomtype;
  }

  public void print(String indent) {
    System.out.println(indent + "TypedIdentIdent");
    ident.print(indent + "    ");
    colon.print(indent + "    ");
    atomtype.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
