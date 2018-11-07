package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class StoDeclChangemode implements IStoDecl {
  private final Changemode changemode;
  private final ITypedIdent typedIdent;

  private StoDeclChangemode(final Changemode changemode, final ITypedIdent typedIdent) {
    this.changemode = changemode;
    this.typedIdent = typedIdent;
  }

  public void print(String indent) {
    System.out.println(indent + "StoDeclChangemode");
    changemode.print(indent + "    ");
    typedIdent.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
