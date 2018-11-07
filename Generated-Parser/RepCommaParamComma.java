package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class RepCommaParamComma implements IRepCommaParam {
  private final Comma comma;
  private final ICpsParam cpsParam;

  private RepCommaParamComma(final Comma comma, final ICpsParam cpsParam) {
    this.comma = comma;
    this.cpsParam = cpsParam;
  }

  public void print(String indent) {
    System.out.println(indent + "RepCommaParamComma");
    comma.print(indent + "    ");
    cpsParam.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
