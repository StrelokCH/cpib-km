package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class DeclFunDecl implements IDecl {
  private final IFunDecl funDecl;

  private DeclFunDecl(final IFunDecl funDecl) {
    this.funDecl = funDecl;
  }

  public void print(String indent) {
    System.out.println(indent + "DeclFunDecl");
    funDecl.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
