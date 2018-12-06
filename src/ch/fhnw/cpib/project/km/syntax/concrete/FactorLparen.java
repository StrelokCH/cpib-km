package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.syntax.abst.IExpression;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class FactorLparen implements IFactor {
  private final LeftParenthesis lparen;
  private final IExpr expr;
  private final RightParenthesis rparen;

  public FactorLparen(final LeftParenthesis lparen, final IExpr expr, final RightParenthesis rparen) {
    this.lparen = lparen;
    this.expr = expr;
    this.rparen = rparen;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(indent + "    T " + lparen.toString());
    expr.print(indent + "    ");
    System.out.println(indent + "    T " + rparen.toString());
  }

  @Override
  public IExpression toAbsSyn() {
    return expr.toAbsSyn();
  }
}
