package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

class FactorLparen implements IFactor {
  private final Lparen lparen;
  private final IExpr expr;
  private final Rparen rparen;

  private FactorLparen(final Lparen lparen, final IExpr expr, final Rparen rparen) {
    this.lparen = lparen;
    this.expr = expr;
    this.rparen = rparen;
  }

  public void print(String indent) {
    System.out.println(indent + "FactorLparen");
    lparen.print(indent + "    ");
    expr.print(indent + "    ");
    rparen.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
