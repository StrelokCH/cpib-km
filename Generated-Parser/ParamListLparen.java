package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

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
    System.out.println(indent + "ParamListLparen");
    lparen.print(indent + "    ");
    optCpsParam.print(indent + "    ");
    rparen.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
