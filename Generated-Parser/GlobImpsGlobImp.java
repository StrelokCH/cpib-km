package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

class GlobImpsGlobImp implements IGlobImps {
  private final IGlobImp globImp;
  private final IRepCommaGlobImps repCommaGlobImps;

  private GlobImpsGlobImp(final IGlobImp globImp, final IRepCommaGlobImps repCommaGlobImps) {
    this.globImp = globImp;
    this.repCommaGlobImps = repCommaGlobImps;
  }

  public void print(String indent) {
    System.out.println(indent + "GlobImpsGlobImp");
    globImp.print(indent + "    ");
    repCommaGlobImps.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
