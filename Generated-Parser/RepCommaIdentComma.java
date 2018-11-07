package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

class RepCommaIdentComma implements IRepCommaIdent {
  private final Comma comma;
  private final IIdents idents;

  private RepCommaIdentComma(final Comma comma, final IIdents idents) {
    this.comma = comma;
    this.idents = idents;
  }

  public void print(String indent) {
    System.out.println(indent + "RepCommaIdentComma");
    comma.print(indent + "    ");
    idents.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
