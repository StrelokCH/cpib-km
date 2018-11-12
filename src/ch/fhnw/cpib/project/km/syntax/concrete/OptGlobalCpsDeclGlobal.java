package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

class OptGlobalCpsDeclGlobal implements IOptGlobalCpsDecl {
  private final Global global;
  private final ICpsDecl cpsDecl;

  private OptGlobalCpsDeclGlobal(final Global global, final ICpsDecl cpsDecl) {
    this.global = global;
    this.cpsDecl = cpsDecl;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(indent + "    T " + global.toString());
    cpsDecl.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
