package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class RepSemicolonCpsStoDeclSemicolon implements IRepSemicolonCpsStoDecl {
  private final Semicolon semicolon;
  private final ICpsStoDecl cpsStoDecl;

  private RepSemicolonCpsStoDeclSemicolon(final Semicolon semicolon, final ICpsStoDecl cpsStoDecl) {
    this.semicolon = semicolon;
    this.cpsStoDecl = cpsStoDecl;
  }

  public void print(String indent) {
    System.out.println(indent + "RepSemicolonCpsStoDeclSemicolon");
    semicolon.print(indent + "    ");
    cpsStoDecl.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
