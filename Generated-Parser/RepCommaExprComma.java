package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class RepCommaExprComma implements IRepCommaExpr {
  private final Comma comma;
  private final ICpsExpr cpsExpr;

  private RepCommaExprComma(final Comma comma, final ICpsExpr cpsExpr) {
    this.comma = comma;
    this.cpsExpr = cpsExpr;
  }

  public void print(String indent) {
    System.out.println(indent + "RepCommaExprComma");
    comma.print(indent + "    ");
    cpsExpr.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
