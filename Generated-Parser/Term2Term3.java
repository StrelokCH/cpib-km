package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class Term2Term3 implements ITerm2 {
  private final ITerm3 term3;
  private final IRepAddoprTerm3 repAddoprTerm3;

  private Term2Term3(final ITerm3 term3, final IRepAddoprTerm3 repAddoprTerm3) {
    this.term3 = term3;
    this.repAddoprTerm3 = repAddoprTerm3;
  }

  public void print(String indent) {
    System.out.println(indent + "Term2Term3");
    term3.print(indent + "    ");
    repAddoprTerm3.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
