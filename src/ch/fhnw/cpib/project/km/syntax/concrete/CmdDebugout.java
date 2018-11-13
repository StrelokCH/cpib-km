package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class CmdDebugout implements ICmd {
  private final DebugOut debugout;
  private final IExpr expr;

  private CmdDebugout(final DebugOut debugout, final IExpr expr) {
    this.debugout = debugout;
    this.expr = expr;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(indent + "    T " + debugout.toString());
    expr.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
