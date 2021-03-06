package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.syntax.abst.IExpression;
import ch.fhnw.cpib.project.km.syntax.abst.LiteralExpr;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class FactorLiteral implements IFactor {
  private final Literal literal;

  public FactorLiteral(final Literal literal) {
    this.literal = literal;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(indent + "    T " + literal.toString());
  }

  @Override
  public IExpression toAbsSyn() {
    return new LiteralExpr(literal);
  }
}
