package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

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
    System.out.println(indent + "ProgParamListLparen");
    lparen.print(indent + "    ");
    optCpsProgParam.print(indent + "    ");
    rparen.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
