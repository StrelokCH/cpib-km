package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

class Ident1 implements IIdent1 {
  private Ident1() {}

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
