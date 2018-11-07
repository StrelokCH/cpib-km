package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

class ProgParamOptFlowmode implements IProgParam {
  private final IOptFlowmode optFlowmode;
  private final IOptChangemode optChangemode;
  private final ITypedIdent typedIdent;

  private ProgParamOptFlowmode(
      final IOptFlowmode optFlowmode,
      final IOptChangemode optChangemode,
      final ITypedIdent typedIdent) {
    this.optFlowmode = optFlowmode;
    this.optChangemode = optChangemode;
    this.typedIdent = typedIdent;
  }

  public void print(String indent) {
    System.out.println(indent + "ProgParamOptFlowmode");
    optFlowmode.print(indent + "    ");
    optChangemode.print(indent + "    ");
    typedIdent.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
