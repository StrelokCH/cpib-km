package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

class StoDeclTypedIdent implements IStoDecl {
  private final ITypedIdent typedIdent;

  private StoDeclTypedIdent(final ITypedIdent typedIdent) {
    this.typedIdent = typedIdent;
  }

  public void print(String indent) {
    System.out.println(indent + "StoDeclTypedIdent");
    typedIdent.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
