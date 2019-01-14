package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class ParamOptFlowmode implements IParam {
  private final IOptFlowmode optFlowmode;
  private final IOptMechmode optMechmode;
  private final IOptChangemode optChangemode;
  private final ITypedIdent typedIdent;

  private ParamOptFlowmode(
      final IOptFlowmode optFlowmode,
      final IOptMechmode optMechmode,
      final IOptChangemode optChangemode,
      final ITypedIdent typedIdent) {
    this.optFlowmode = optFlowmode;
    this.optMechmode = optMechmode;
    this.optChangemode = optChangemode;
    this.typedIdent = typedIdent;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    optFlowmode.print(indent + "    ");
    optMechmode.print(indent + "    ");
    optChangemode.print(indent + "    ");
    typedIdent.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
