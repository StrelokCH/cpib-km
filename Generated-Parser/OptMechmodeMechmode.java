package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class OptMechmodeMechmode implements IOptMechmode {
  private final Mechmode mechmode;

  private OptMechmodeMechmode(final Mechmode mechmode) {
    this.mechmode = mechmode;
  }

  public void print(String indent) {
    System.out.println(indent + "OptMechmodeMechmode");
    mechmode.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
