package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class OptFlowmodeFlowmode implements IOptFlowmode {
  private final Flowmode flowmode;

  private OptFlowmodeFlowmode(final Flowmode flowmode) {
    this.flowmode = flowmode;
  }

  public void print(String indent) {
    System.out.println(indent + "OptFlowmodeFlowmode");
    flowmode.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
