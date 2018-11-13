package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class OptLocalCpsStoDeclLocal implements IOptLocalCpsStoDecl {
  private final Local local;
  private final ICpsStoDecl cpsStoDecl;

  private OptLocalCpsStoDeclLocal(final Local local, final ICpsStoDecl cpsStoDecl) {
    this.local = local;
    this.cpsStoDecl = cpsStoDecl;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(indent + "    T " + local.toString());
    cpsStoDecl.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
