package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

class CmdAWhile implements ICmd {
  private final AWhile aWhile;
  private final IExpr expr;
  private final ADo aDo;
  private final ICpsCmd cpsCmd;
  private final Endwhile endwhile;

  private CmdAWhile(
      final AWhile aWhile,
      final IExpr expr,
      final ADo aDo,
      final ICpsCmd cpsCmd,
      final Endwhile endwhile) {
    this.aWhile = aWhile;
    this.expr = expr;
    this.aDo = aDo;
    this.cpsCmd = cpsCmd;
    this.endwhile = endwhile;
  }

  public void print(String indent) {
    System.out.println(indent + "CmdAWhile");
    aWhile.print(indent + "    ");
    expr.print(indent + "    ");
    aDo.print(indent + "    ");
    cpsCmd.print(indent + "    ");
    endwhile.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
