package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class OptGlobInitsGlobInits implements IOptGlobInits {
  private final IGlobInits globInits;

  private OptGlobInitsGlobInits(final IGlobInits globInits) {
    this.globInits = globInits;
  }

  public void print(String indent) {
    System.out.println(indent + "OptGlobInitsGlobInits");
    globInits.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
