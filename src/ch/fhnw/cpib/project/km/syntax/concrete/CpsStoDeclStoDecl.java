package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class CpsStoDeclStoDecl implements ICpsStoDecl {
  private final IStoDecl stoDecl;
  private final IRepSemicolonCpsStoDecl repSemicolonCpsStoDecl;

  public CpsStoDeclStoDecl(
      final IStoDecl stoDecl, final IRepSemicolonCpsStoDecl repSemicolonCpsStoDecl) {
    this.stoDecl = stoDecl;
    this.repSemicolonCpsStoDecl = repSemicolonCpsStoDecl;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    stoDecl.print(indent + "    ");
    repSemicolonCpsStoDecl.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
