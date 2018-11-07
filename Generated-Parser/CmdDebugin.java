package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class CmdDebugin implements ICmd {
  private final Debugin debugin;
  private final IExpr expr;

  private CmdDebugin(final Debugin debugin, final IExpr expr) {
    this.debugin = debugin;
    this.expr = expr;
  }

  public void print(String indent) {
    System.out.println(indent + "CmdDebugin");
    debugin.print(indent + "    ");
    expr.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
