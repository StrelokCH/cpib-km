package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class CmdExpr implements ICmd {
  private final IExpr expr;
  private final Becomes becomes;
  private final IExpr expr2;

  public CmdExpr(final IExpr expr, final Becomes becomes, final IExpr expr2) {
    this.expr = expr;
    this.becomes = becomes;
    this.expr2 = expr2;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    expr.print(indent + "    ");
    System.out.println(indent + "    T " + becomes.toString());
    expr.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
