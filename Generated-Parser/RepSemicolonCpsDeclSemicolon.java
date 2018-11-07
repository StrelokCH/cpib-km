package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class RepSemicolonCpsDeclSemicolon implements IRepSemicolonCpsDecl {
  private final Semicolon semicolon;
  private final ICpsDecl cpsDecl;

  private RepSemicolonCpsDeclSemicolon(final Semicolon semicolon, final ICpsDecl cpsDecl) {
    this.semicolon = semicolon;
    this.cpsDecl = cpsDecl;
  }

  public void print(String indent) {
    System.out.println(indent + "RepSemicolonCpsDeclSemicolon");
    semicolon.print(indent + "    ");
    cpsDecl.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
