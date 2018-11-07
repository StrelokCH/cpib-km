package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class OptLocalCpsStoDeclLocal implements IOptLocalCpsStoDecl {
  private final Local local;
  private final ICpsStoDecl cpsStoDecl;

  private OptLocalCpsStoDeclLocal(final Local local, final ICpsStoDecl cpsStoDecl) {
    this.local = local;
    this.cpsStoDecl = cpsStoDecl;
  }

  public void print(String indent) {
    System.out.println(indent + "OptLocalCpsStoDeclLocal");
    local.print(indent + "    ");
    cpsStoDecl.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
