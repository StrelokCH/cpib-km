package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

class OptChangemodeChangemode implements IOptChangemode {
  private final Changemode changemode;

  private OptChangemodeChangemode(final Changemode changemode) {
    this.changemode = changemode;
  }

  public void print(String indent) {
    System.out.println(indent + "OptChangemodeChangemode");
    changemode.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
