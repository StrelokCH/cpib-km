package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class OptReloprTerm2Relopr implements IOptReloprTerm2 {
  private final RelationalOperator relopr;
  private final ITerm2 term2;

  private OptReloprTerm2Relopr(final RelationalOperator relopr, final ITerm2 term2) {
    this.relopr = relopr;
    this.term2 = term2;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(indent + "    T " + relopr.toString());
    term2.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
