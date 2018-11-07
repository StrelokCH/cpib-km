package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

class CpsProgParamProgParam implements ICpsProgParam {
  private final IProgParam progParam;
  private final IRepCommaProgParam repCommaProgParam;

  private CpsProgParamProgParam(
      final IProgParam progParam, final IRepCommaProgParam repCommaProgParam) {
    this.progParam = progParam;
    this.repCommaProgParam = repCommaProgParam;
  }

  public void print(String indent) {
    System.out.println(indent + "CpsProgParamProgParam");
    progParam.print(indent + "    ");
    repCommaProgParam.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
