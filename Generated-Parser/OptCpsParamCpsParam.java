package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class OptCpsParamCpsParam implements IOptCpsParam {
  private final ICpsParam cpsParam;

  private OptCpsParamCpsParam(final ICpsParam cpsParam) {
    this.cpsParam = cpsParam;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    cpsParam.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
