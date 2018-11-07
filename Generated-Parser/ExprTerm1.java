package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

class ExprTerm1 implements IExpr {
  private final ITerm1 term1;
  private final IRepBooloprExpr repBooloprExpr;

  private ExprTerm1(final ITerm1 term1, final IRepBooloprExpr repBooloprExpr) {
    this.term1 = term1;
    this.repBooloprExpr = repBooloprExpr;
  }

  public void print(String indent) {
    System.out.println(indent + "ExprTerm1");
    term1.print(indent + "    ");
    repBooloprExpr.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
