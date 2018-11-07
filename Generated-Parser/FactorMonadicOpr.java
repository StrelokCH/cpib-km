package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class FactorMonadicOpr implements IFactor {
  private final IMonadicOpr monadicOpr;
  private final IFactor factor;

  private FactorMonadicOpr(final IMonadicOpr monadicOpr, final IFactor factor) {
    this.monadicOpr = monadicOpr;
    this.factor = factor;
  }

  public void print(String indent) {
    System.out.println(indent + "FactorMonadicOpr");
    monadicOpr.print(indent + "    ");
    factor.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
