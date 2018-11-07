package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

class FactorLiteral implements IFactor {
  private final Literal literal;

  private FactorLiteral(final Literal literal) {
    this.literal = literal;
  }

  public void print(String indent) {
    System.out.println(indent + "FactorLiteral");
    literal.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
