package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class RepAddoprTerm3 implements IRepAddoprTerm3 {
  private RepAddoprTerm3() {}

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
