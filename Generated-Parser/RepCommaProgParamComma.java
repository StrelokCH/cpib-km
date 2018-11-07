package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class RepCommaProgParamComma implements IRepCommaProgParam {
  private final Comma comma;
  private final ICpsProgParam cpsProgParam;

  private RepCommaProgParamComma(final Comma comma, final ICpsProgParam cpsProgParam) {
    this.comma = comma;
    this.cpsProgParam = cpsProgParam;
  }

  public void print(String indent) {
    System.out.println(indent + "RepCommaProgParamComma");
    comma.print(indent + "    ");
    cpsProgParam.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
