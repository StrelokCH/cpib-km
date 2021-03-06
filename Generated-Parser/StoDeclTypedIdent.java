package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class StoDeclTypedIdent implements IStoDecl {
  private final ITypedIdent typedIdent;

  private StoDeclTypedIdent(final ITypedIdent typedIdent) {
    this.typedIdent = typedIdent;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    typedIdent.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
