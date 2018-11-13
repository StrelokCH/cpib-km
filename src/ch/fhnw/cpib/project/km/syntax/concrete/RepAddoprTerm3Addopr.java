package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class RepAddoprTerm3Addopr implements IRepAddoprTerm3 {
  private final AddOperator addopr;
  private final ITerm2 term2;

  private RepAddoprTerm3Addopr(final AddOperator addopr, final ITerm2 term2) {
    this.addopr = addopr;
    this.term2 = term2;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(indent + "    T " + addopr.toString());
    term2.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
