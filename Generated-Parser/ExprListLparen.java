package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class ExprListLparen implements IExprList {
  private final Lparen lparen;
  private final IOptCpsExpr optCpsExpr;
  private final Rparen rparen;

  private ExprListLparen(final Lparen lparen, final IOptCpsExpr optCpsExpr, final Rparen rparen) {
    this.lparen = lparen;
    this.optCpsExpr = optCpsExpr;
    this.rparen = rparen;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(ident + "    T " + lparen.toString());
    optCpsExpr.print(indent + "    ");
    System.out.println(ident + "    T " + rparen.toString());
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
