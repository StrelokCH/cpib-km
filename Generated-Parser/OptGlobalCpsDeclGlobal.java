package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class OptGlobalCpsDeclGlobal implements IOptGlobalCpsDecl {
  private final Global global;
  private final ICpsDecl cpsDecl;

  private OptGlobalCpsDeclGlobal(final Global global, final ICpsDecl cpsDecl) {
    this.global = global;
    this.cpsDecl = cpsDecl;
  }

  public void print(String indent) {
    System.out.println(indent + "OptGlobalCpsDeclGlobal");
    global.print(indent + "    ");
    cpsDecl.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
