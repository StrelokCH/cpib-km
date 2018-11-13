package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class DeclFunDecl implements IDecl {
  private final IFunDecl funDecl;

  private DeclFunDecl(final IFunDecl funDecl) {
    this.funDecl = funDecl;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    funDecl.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
