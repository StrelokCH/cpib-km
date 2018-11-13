package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class CmdIf implements ICmd {
  private final If aIf;
  private final IExpr expr;
  private final Then then;
  private final ICpsCmd cpsCmd;
  private final Else aElse;
  private final ICpsCmd cpsCmd;
  private final Endif endif;

  private CmdIf(
      final If aIf,
      final IExpr expr,
      final Then then,
      final ICpsCmd cpsCmd,
      final Else aElse,
      final ICpsCmd cpsCmd,
      final Endif endif) {
    this.aIf = aIf;
    this.expr = expr;
    this.then = then;
    this.cpsCmd = cpsCmd;
    this.aElse = aElse;
    this.cpsCmd = cpsCmd;
    this.endif = endif;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(ident + "    T " + aIf.toString());
    expr.print(indent + "    ");
    System.out.println(ident + "    T " + then.toString());
    cpsCmd.print(indent + "    ");
    System.out.println(ident + "    T " + aElse.toString());
    cpsCmd.print(indent + "    ");
    System.out.println(ident + "    T " + endif.toString());
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
