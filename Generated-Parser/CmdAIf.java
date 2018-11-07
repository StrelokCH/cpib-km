package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

class CmdAIf implements ICmd {
  private final AIf aIf;
  private final IExpr expr;
  private final Then then;
  private final ICpsCmd cpsCmd;
  private final AElse aElse;
  private final ICpsCmd cpsCmd;
  private final Endif endif;

  private CmdAIf(
      final AIf aIf,
      final IExpr expr,
      final Then then,
      final ICpsCmd cpsCmd,
      final AElse aElse,
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
    System.out.println(indent + "CmdAIf");
    aIf.print(indent + "    ");
    expr.print(indent + "    ");
    then.print(indent + "    ");
    cpsCmd.print(indent + "    ");
    aElse.print(indent + "    ");
    cpsCmd.print(indent + "    ");
    endif.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
