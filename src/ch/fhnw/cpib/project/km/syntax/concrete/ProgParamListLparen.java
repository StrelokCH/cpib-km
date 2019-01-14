package ch.fhnw.cpib.project.km.syntax.concrete;

import java.util.List;

import ch.fhnw.cpib.project.km.syntax.abst.FullIdentifier;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class ProgParamListLparen implements IProgParamList {
  private final LeftParenthesis lparen;
  private final IOptCpsProgParam optCpsProgParam;
  private final RightParenthesis rparen;

  public ProgParamListLparen(
      final LeftParenthesis lparen, final IOptCpsProgParam optCpsProgParam, final RightParenthesis rparen) {
    this.lparen = lparen;
    this.optCpsProgParam = optCpsProgParam;
    this.rparen = rparen;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(indent + "    T " + lparen.toString());
    optCpsProgParam.print(indent + "    ");
    System.out.println(indent + "    T " + rparen.toString());
  }

  @Override
  public List<FullIdentifier> toAbsSyn() {
    return optCpsProgParam.toAbsSyn();
  }
}
