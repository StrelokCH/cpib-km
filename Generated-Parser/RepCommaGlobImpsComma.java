package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class RepCommaGlobImpsComma implements IRepCommaGlobImps {
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
    System.out.println(indent + "RepCommaGlobImpsComma");
    comma.print(indent + "    ");
    globImp.print(indent + "    ");
    repCommaGlobImps.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
