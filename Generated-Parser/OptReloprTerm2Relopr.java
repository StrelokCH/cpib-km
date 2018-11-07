package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class OptReloprTerm2Relopr implements IOptReloprTerm2 {
  private final Relopr relopr;
  private final ITerm2 term2;

  private OptReloprTerm2Relopr(final Relopr relopr, final ITerm2 term2) {
    this.relopr = relopr;
    this.term2 = term2;
  }

  public void print(String indent) {
    System.out.println(indent + "OptReloprTerm2Relopr");
    relopr.print(indent + "    ");
    term2.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
