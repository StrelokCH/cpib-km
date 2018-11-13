package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class StoDeclChangemode implements IStoDecl {
  private final ChangeMode changemode;
  private final ITypedIdent typedIdent;

  private StoDeclChangemode(final ChangeMode changemode, final ITypedIdent typedIdent) {
    this.changemode = changemode;
    this.typedIdent = typedIdent;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(indent + "    T " + changemode.toString());
    typedIdent.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
