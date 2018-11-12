package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

class GlobImpsGlobImp implements IGlobImps {
  private final IGlobImp globImp;
  private final IRepCommaGlobImps repCommaGlobImps;

  private GlobImpsGlobImp(final IGlobImp globImp, final IRepCommaGlobImps repCommaGlobImps) {
    this.globImp = globImp;
    this.repCommaGlobImps = repCommaGlobImps;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    globImp.print(indent + "    ");
    repCommaGlobImps.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
