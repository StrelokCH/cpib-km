package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

class GlobInitsInit implements IGlobInits {
  private final Init init;
  private final IIdents idents;

  private GlobInitsInit(final Init init, final IIdents idents) {
    this.init = init;
    this.idents = idents;
  }

  public void print(String indent) {
    System.out.println(indent + "GlobInitsInit");
    init.print(indent + "    ");
    idents.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
