package ch.fhnw.cpib.project.km.syntax.concrete;
import ch.fhnw.cpib.project.km.syntax.abst.IExpression;

import java.util.List;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class CmdDebugin implements ICmd {
  private final DebugIn debugin;
  private final IExpr expr;

  public CmdDebugin(final DebugIn debugin, final IExpr expr) {
    this.debugin = debugin;
    this.expr = expr;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(indent + "    T " + debugin.toString());
    expr.print(indent + "    ");
  }

  @Override
  public List<IExpression> toAbsSyn() {
    return null;
  }
}
