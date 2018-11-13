package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class ParamListLparen implements IParamList {
  private final LeftParenthesis lparen;
  private final IOptCpsParam optCpsParam;
  private final RightParenthesis rparen;

  private ParamListLparen(
      final LeftParenthesis lparen, final IOptCpsParam optCpsParam, final RightParenthesis rparen) {
    this.lparen = lparen;
    this.optCpsParam = optCpsParam;
    this.rparen = rparen;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(indent + "    T " + lparen.toString());
    optCpsParam.print(indent + "    ");
    System.out.println(indent + "    T " + rparen.toString());
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
