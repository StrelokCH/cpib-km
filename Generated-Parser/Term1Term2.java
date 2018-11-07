package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;

class Term1Term2 implements ITerm1 {
  private final ITerm2 term2;
  private final IOptReloprTerm2 optReloprTerm2;

  private Term1Term2(final ITerm2 term2, final IOptReloprTerm2 optReloprTerm2) {
    this.term2 = term2;
    this.optReloprTerm2 = optReloprTerm2;
  }

  public void print(String indent) {
    System.out.println(indent + "Term1Term2");
    term2.print(indent + "    ");
    optReloprTerm2.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
