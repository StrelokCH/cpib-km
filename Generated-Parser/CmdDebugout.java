package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class CmdDebugout implements ICmd {
  private final Debugout debugout;
  private final IExpr expr;

  private CmdDebugout(final Debugout debugout, final IExpr expr) {
    this.debugout = debugout;
    this.expr = expr;
  }

  public void print(String indent) {
    System.out.println(indent + "CmdDebugout");
    debugout.print(indent + "    ");
    expr.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
