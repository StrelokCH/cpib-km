package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;

import java.util.List;

import ch.fhnw.cpib.project.km.syntax.abst.IExpression;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class CmdSkip implements ICmd {
  private final Skip skip;

  public CmdSkip(final Skip skip) {
    this.skip = skip;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(indent + "    T " + skip.toString());
  }

  @Override
  public List<IExpression> toAbsSyn() {
    return null;
  }
}
