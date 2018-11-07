package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class OptFlowmode implements IOptFlowmode {
  private OptFlowmode() {}

  public void print(String indent) {
    System.out.println(indent + "OptFlowmode");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
