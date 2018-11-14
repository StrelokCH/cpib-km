package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class OptCpsExprCpsExpr implements IOptCpsExpr {
  private final ICpsExpr cpsExpr;

  public OptCpsExprCpsExpr(final ICpsExpr cpsExpr) {
    this.cpsExpr = cpsExpr;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    cpsExpr.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
