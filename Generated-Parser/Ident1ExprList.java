package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

class Ident1ExprList implements IIdent1 {
  private final IExprList exprList;

  private Ident1ExprList(final IExprList exprList) {
    this.exprList = exprList;
  }

  public void print(String indent) {
    System.out.println(indent + "Ident1ExprList");
    exprList.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
