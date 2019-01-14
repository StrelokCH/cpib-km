package ch.fhnw.cpib.project.km.syntax.concrete;

import java.util.List;

import ch.fhnw.cpib.project.km.syntax.abst.FullIdentifier;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class RepCommaProgParamComma implements IRepCommaProgParam {
  private final Comma comma;
  private final ICpsProgParam cpsProgParam;

  public RepCommaProgParamComma(final Comma comma, final ICpsProgParam cpsProgParam) {
    this.comma = comma;
    this.cpsProgParam = cpsProgParam;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(indent + "    T " + comma.toString());
    cpsProgParam.print(indent + "    ");
  }

  @Override
  public List<FullIdentifier> toAbsSyn() {
    return cpsProgParam.toAbsSyn();
  }
}
