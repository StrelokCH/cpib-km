package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

class GlobImpOptFlowmode implements IGlobImp {
  private final IOptFlowmode optFlowmode;
  private final IOptChangemode optChangemode;
  private final Ident ident;

  private GlobImpOptFlowmode(
      final IOptFlowmode optFlowmode, final IOptChangemode optChangemode, final Ident ident) {
    this.optFlowmode = optFlowmode;
    this.optChangemode = optChangemode;
    this.ident = ident;
  }

  public void print(String indent) {
    System.out.println(indent + "GlobImpOptFlowmode");
    optFlowmode.print(indent + "    ");
    optChangemode.print(indent + "    ");
    ident.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
