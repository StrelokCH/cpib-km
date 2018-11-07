package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

class RepMultoprFactorMultopr implements IRepMultoprFactor {
  private final Multopr multopr;
  private final ITerm3 term3;

  private RepMultoprFactorMultopr(final Multopr multopr, final ITerm3 term3) {
    this.multopr = multopr;
    this.term3 = term3;
  }

  public void print(String indent) {
    System.out.println(indent + "RepMultoprFactorMultopr");
    multopr.print(indent + "    ");
    term3.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
