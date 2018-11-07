package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

class ExprListLparen implements IExprList {
  private final Lparen lparen;
  private final IOptCpsExpr optCpsExpr;
  private final Rparen rparen;

  private ExprListLparen(final Lparen lparen, final IOptCpsExpr optCpsExpr, final Rparen rparen) {
    this.lparen = lparen;
    this.optCpsExpr = optCpsExpr;
    this.rparen = rparen;
  }

  public void print(String indent) {
    System.out.println(indent + "ExprListLparen");
    lparen.print(indent + "    ");
    optCpsExpr.print(indent + "    ");
    rparen.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
