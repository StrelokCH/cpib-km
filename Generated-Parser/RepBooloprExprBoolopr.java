package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class RepBooloprExprBoolopr implements IRepBooloprExpr {
  private final Boolopr boolopr;
  private final IExpr expr;

  private RepBooloprExprBoolopr(final Boolopr boolopr, final IExpr expr) {
    this.boolopr = boolopr;
    this.expr = expr;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(ident + "    T " + boolopr.toString());
    expr.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
