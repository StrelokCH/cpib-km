package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

class OptCpsExprCpsExpr implements IOptCpsExpr {
  private final ICpsExpr cpsExpr;

  private OptCpsExprCpsExpr(final ICpsExpr cpsExpr) {
    this.cpsExpr = cpsExpr;
  }

  public void print(String indent) {
    System.out.println(indent + "OptCpsExprCpsExpr");
    cpsExpr.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
