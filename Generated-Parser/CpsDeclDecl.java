package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

class CpsDeclDecl implements ICpsDecl {
  private final IDecl decl;
  private final IRepSemicolonCpsDecl repSemicolonCpsDecl;

  private CpsDeclDecl(final IDecl decl, final IRepSemicolonCpsDecl repSemicolonCpsDecl) {
    this.decl = decl;
    this.repSemicolonCpsDecl = repSemicolonCpsDecl;
  }

  public void print(String indent) {
    System.out.println(indent + "CpsDeclDecl");
    decl.print(indent + "    ");
    repSemicolonCpsDecl.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
