package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

class MonadicOprNot implements IMonadicOpr {
  private final Not not;

  private MonadicOprNot(final Not not) {
    this.not = not;
  }

  public void print(String indent) {
    System.out.println(indent + "MonadicOprNot");
    not.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
