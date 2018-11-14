package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class CpsDeclDecl implements ICpsDecl {
  private final IDecl decl;
  private final IRepSemicolonCpsDecl repSemicolonCpsDecl;

  public CpsDeclDecl(final IDecl decl, final IRepSemicolonCpsDecl repSemicolonCpsDecl) {
    this.decl = decl;
    this.repSemicolonCpsDecl = repSemicolonCpsDecl;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    decl.print(indent + "    ");
    repSemicolonCpsDecl.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
