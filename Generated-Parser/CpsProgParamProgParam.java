package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

class CpsProgParamProgParam implements ICpsProgParam {
  private final IProgParam progParam;
  private final IRepCommaProgParam repCommaProgParam;

  private CpsProgParamProgParam(
      final IProgParam progParam, final IRepCommaProgParam repCommaProgParam) {
    this.progParam = progParam;
    this.repCommaProgParam = repCommaProgParam;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    progParam.print(indent + "    ");
    repCommaProgParam.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
