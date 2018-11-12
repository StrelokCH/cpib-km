package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

class DeclProcDecl implements IDecl {
  private final IProcDecl procDecl;

  private DeclProcDecl(final IProcDecl procDecl) {
    this.procDecl = procDecl;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    procDecl.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
