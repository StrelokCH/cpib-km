package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

class ParamListLparen implements IParamList {
  private final Lparen lparen;
  private final IOptCpsParam optCpsParam;
  private final Rparen rparen;

  private ParamListLparen(
      final Lparen lparen, final IOptCpsParam optCpsParam, final Rparen rparen) {
    this.lparen = lparen;
    this.optCpsParam = optCpsParam;
    this.rparen = rparen;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(ident + "    T " + lparen.toString());
    optCpsParam.print(indent + "    ");
    System.out.println(ident + "    T " + rparen.toString());
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
