package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class GlobalGlobImpsGlobal implements IGlobalGlobImps {
  private final Global global;
  private final IGlobImps globImps;

  private GlobalGlobImpsGlobal(final Global global, final IGlobImps globImps) {
    this.global = global;
    this.globImps = globImps;
  }

  public void print(String indent) {
    System.out.println(indent + "GlobalGlobImpsGlobal");
    global.print(indent + "    ");
    globImps.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
