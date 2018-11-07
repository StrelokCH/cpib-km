package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

class Term3Factor implements ITerm3 {
  private final IFactor factor;
  private final IRepMultoprFactor repMultoprFactor;

  private Term3Factor(final IFactor factor, final IRepMultoprFactor repMultoprFactor) {
    this.factor = factor;
    this.repMultoprFactor = repMultoprFactor;
  }

  public void print(String indent) {
    System.out.println(indent + "Term3Factor");
    factor.print(indent + "    ");
    repMultoprFactor.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
