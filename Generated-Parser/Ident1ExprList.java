package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class Ident1ExprList implements IIdent1 {
  private final IExprList exprList;

  private Ident1ExprList(final IExprList exprList) {
    this.exprList = exprList;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    exprList.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
