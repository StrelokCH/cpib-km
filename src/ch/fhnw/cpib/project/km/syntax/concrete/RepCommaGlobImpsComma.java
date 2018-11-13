package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class RepCommaGlobImpsComma implements IRepCommaGlobImps {
  private final Comma comma;
  private final IGlobImp globImp;
  private final IRepCommaGlobImps repCommaGlobImps;

  private RepCommaGlobImpsComma(
      final Comma comma, final IGlobImp globImp, final IRepCommaGlobImps repCommaGlobImps) {
    this.comma = comma;
    this.globImp = globImp;
    this.repCommaGlobImps = repCommaGlobImps;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(indent + "    T " + comma.toString());
    globImp.print(indent + "    ");
    repCommaGlobImps.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
