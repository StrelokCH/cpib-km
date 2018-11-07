package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

class ProgParamListLparen implements IProgParamList {
  private final Lparen lparen;
  private final IOptCpsProgParam optCpsProgParam;
  private final Rparen rparen;

  private ProgParamListLparen(
      final Lparen lparen, final IOptCpsProgParam optCpsProgParam, final Rparen rparen) {
    this.lparen = lparen;
    this.optCpsProgParam = optCpsProgParam;
    this.rparen = rparen;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(ident + "    T " + lparen.toString());
    optCpsProgParam.print(indent + "    ");
    System.out.println(ident + "    T " + rparen.toString());
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
