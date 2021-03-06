package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class CmdWhile implements ICmd {
  private final While aWhile;
  private final IExpr expr;
  private final Do aDo;
  private final ICpsCmd cpsCmd;
  private final Endwhile endwhile;

  private CmdWhile(
      final While aWhile,
      final IExpr expr,
      final Do aDo,
      final ICpsCmd cpsCmd,
      final Endwhile endwhile) {
    this.aWhile = aWhile;
    this.expr = expr;
    this.aDo = aDo;
    this.cpsCmd = cpsCmd;
    this.endwhile = endwhile;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(ident + "    T " + aWhile.toString());
    expr.print(indent + "    ");
    System.out.println(ident + "    T " + aDo.toString());
    cpsCmd.print(indent + "    ");
    System.out.println(ident + "    T " + endwhile.toString());
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
