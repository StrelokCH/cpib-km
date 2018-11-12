package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

class DeclStoDecl implements IDecl {
  private final IStoDecl stoDecl;

  private DeclStoDecl(final IStoDecl stoDecl) {
    this.stoDecl = stoDecl;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    stoDecl.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
