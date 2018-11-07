package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

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
    System.out.println(indent + "CmdExpr");
    expr.print(indent + "    ");
    becomes.print(indent + "    ");
    expr.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
