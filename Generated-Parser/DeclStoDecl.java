package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

class DeclStoDecl implements IDecl {
  private final IStoDecl stoDecl;

  private DeclStoDecl(final IStoDecl stoDecl) {
    this.stoDecl = stoDecl;
  }

  public void print(String indent) {
    System.out.println(indent + "DeclStoDecl");
    stoDecl.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
