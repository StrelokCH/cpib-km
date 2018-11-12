package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

class CpsParamParam implements ICpsParam {
  private final IParam param;
  private final IRepCommaParam repCommaParam;

  private CpsParamParam(final IParam param, final IRepCommaParam repCommaParam) {
    this.param = param;
    this.repCommaParam = repCommaParam;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    param.print(indent + "    ");
    repCommaParam.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
