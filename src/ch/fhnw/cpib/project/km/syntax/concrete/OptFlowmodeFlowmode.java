package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

class OptFlowmodeFlowmode implements IOptFlowmode {
  private final Flowmode flowmode;

  private OptFlowmodeFlowmode(final Flowmode flowmode) {
    this.flowmode = flowmode;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(indent + "    T " + flowmode.toString());
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
