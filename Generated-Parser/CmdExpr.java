package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

class CmdExpr implements ICmd {
  private final IExpr expr;
  private final Becomes becomes;
  private final IExpr expr;

  private CmdExpr(final IExpr expr, final Becomes becomes, final IExpr expr) {
    this.expr = expr;
    this.becomes = becomes;
    this.expr = expr;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    expr.print(indent + "    ");
    System.out.println(ident + "    T " + becomes.toString());
    expr.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
