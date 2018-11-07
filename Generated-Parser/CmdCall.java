package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

class CmdCall implements ICmd {
  private final Call call;
  private final Ident ident;
  private final IExprList exprList;
  private final IOptGlobInits optGlobInits;

  private CmdCall(
      final Call call,
      final Ident ident,
      final IExprList exprList,
      final IOptGlobInits optGlobInits) {
    this.call = call;
    this.ident = ident;
    this.exprList = exprList;
    this.optGlobInits = optGlobInits;
  }

  public void print(String indent) {
    System.out.println(indent + "CmdCall");
    call.print(indent + "    ");
    ident.print(indent + "    ");
    exprList.print(indent + "    ");
    optGlobInits.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
