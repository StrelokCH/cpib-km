package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

class OptCpsParamCpsParam implements IOptCpsParam {
  private final ICpsParam cpsParam;

  private OptCpsParamCpsParam(final ICpsParam cpsParam) {
    this.cpsParam = cpsParam;
  }

  public void print(String indent) {
    System.out.println(indent + "OptCpsParamCpsParam");
    cpsParam.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
