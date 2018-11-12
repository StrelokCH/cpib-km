package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

class CpsExprExpr implements ICpsExpr {
  private final IExpr expr;
  private final IRepCommaExpr repCommaExpr;

  private CpsExprExpr(final IExpr expr, final IRepCommaExpr repCommaExpr) {
    this.expr = expr;
    this.repCommaExpr = repCommaExpr;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    expr.print(indent + "    ");
    repCommaExpr.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
