package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class MonadicOprAddopr implements IMonadicOpr {
  private final Addopr addopr;

  private MonadicOprAddopr(final Addopr addopr) {
    this.addopr = addopr;
  }

  public void print(String indent) {
    System.out.println(indent + "MonadicOprAddopr");
    addopr.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
