package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

class OptCpsProgParamCpsProgParam implements IOptCpsProgParam {
  private final ICpsProgParam cpsProgParam;

  private OptCpsProgParamCpsProgParam(final ICpsProgParam cpsProgParam) {
    this.cpsProgParam = cpsProgParam;
  }

  public void print(String indent) {
    System.out.println(indent + "OptCpsProgParamCpsProgParam");
    cpsProgParam.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
