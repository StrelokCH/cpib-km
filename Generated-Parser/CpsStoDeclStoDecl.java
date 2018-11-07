package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class CpsStoDeclStoDecl implements ICpsStoDecl {
  private final IStoDecl stoDecl;
  private final IRepSemicolonCpsStoDecl repSemicolonCpsStoDecl;

  private CpsStoDeclStoDecl(
      final IStoDecl stoDecl, final IRepSemicolonCpsStoDecl repSemicolonCpsStoDecl) {
    this.stoDecl = stoDecl;
    this.repSemicolonCpsStoDecl = repSemicolonCpsStoDecl;
  }

  public void print(String indent) {
    System.out.println(indent + "CpsStoDeclStoDecl");
    stoDecl.print(indent + "    ");
    repSemicolonCpsStoDecl.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
