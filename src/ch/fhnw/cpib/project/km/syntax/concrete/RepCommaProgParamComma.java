package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class RepCommaProgParamComma implements IRepCommaProgParam {
  private final Comma comma;
  private final ICpsProgParam cpsProgParam;

  private RepCommaProgParamComma(final Comma comma, final ICpsProgParam cpsProgParam) {
    this.comma = comma;
    this.cpsProgParam = cpsProgParam;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(indent + "    T " + comma.toString());
    cpsProgParam.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
