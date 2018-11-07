package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

class RepSemicolonCpsDeclSemicolon implements IRepSemicolonCpsDecl {
  private final Semicolon semicolon;
  private final ICpsDecl cpsDecl;

  private RepSemicolonCpsDeclSemicolon(final Semicolon semicolon, final ICpsDecl cpsDecl) {
    this.semicolon = semicolon;
    this.cpsDecl = cpsDecl;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(ident + "    T " + semicolon.toString());
    cpsDecl.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
