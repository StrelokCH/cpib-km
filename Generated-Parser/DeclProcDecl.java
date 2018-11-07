package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

class DeclProcDecl implements IDecl {
  private final IProcDecl procDecl;

  private DeclProcDecl(final IProcDecl procDecl) {
    this.procDecl = procDecl;
  }

  public void print(String indent) {
    System.out.println(indent + "DeclProcDecl");
    procDecl.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
