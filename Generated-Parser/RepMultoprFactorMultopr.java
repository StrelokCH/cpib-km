package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class RepMultoprFactorMultopr implements IRepMultoprFactor {
  private final Multopr multopr;
  private final ITerm3 term3;

  private RepMultoprFactorMultopr(final Multopr multopr, final ITerm3 term3) {
    this.multopr = multopr;
    this.term3 = term3;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(ident + "    T " + multopr.toString());
    term3.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
