package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class FactorLparen implements IFactor {
  private final Lparen lparen;
  private final IExpr expr;
  private final Rparen rparen;

  private FactorLparen(final Lparen lparen, final IExpr expr, final Rparen rparen) {
    this.lparen = lparen;
    this.expr = expr;
    this.rparen = rparen;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(ident + "    T " + lparen.toString());
    expr.print(indent + "    ");
    System.out.println(ident + "    T " + rparen.toString());
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
