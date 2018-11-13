package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class RepCommaExprComma implements IRepCommaExpr {
  private final Comma comma;
  private final ICpsExpr cpsExpr;

  private RepCommaExprComma(final Comma comma, final ICpsExpr cpsExpr) {
    this.comma = comma;
    this.cpsExpr = cpsExpr;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(indent + "    T " + comma.toString());
    cpsExpr.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
