package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

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
    System.out.println(indent + this.getClass().getSimpleName());
    optFlowmode.print(indent + "    ");
    optChangemode.print(indent + "    ");
    System.out.println(ident + "    T " + ident.toString());
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
