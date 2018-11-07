package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class RepBooloprExprBoolopr implements IRepBooloprExpr {
  private final Boolopr boolopr;
  private final IExpr expr;

  private RepBooloprExprBoolopr(final Boolopr boolopr, final IExpr expr) {
    this.boolopr = boolopr;
    this.expr = expr;
  }

  public void print(String indent) {
    System.out.println(indent + "RepBooloprExprBoolopr");
    boolopr.print(indent + "    ");
    expr.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
