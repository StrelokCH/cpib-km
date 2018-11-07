package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class RepAddoprTerm3Addopr implements IRepAddoprTerm3 {
  private final Addopr addopr;
  private final ITerm2 term2;

  private RepAddoprTerm3Addopr(final Addopr addopr, final ITerm2 term2) {
    this.addopr = addopr;
    this.term2 = term2;
  }

  public void print(String indent) {
    System.out.println(indent + "RepAddoprTerm3Addopr");
    addopr.print(indent + "    ");
    term2.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
