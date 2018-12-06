package ch.fhnw.cpib.project.km.syntax.concrete;

import java.util.List;

import ch.fhnw.cpib.project.km.syntax.abst.FullIdentifier;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class OptCpsProgParamCpsProgParam implements IOptCpsProgParam {
  private final ICpsProgParam cpsProgParam;

  public OptCpsProgParamCpsProgParam(final ICpsProgParam cpsProgParam) {
    this.cpsProgParam = cpsProgParam;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    cpsProgParam.print(indent + "    ");
  }

  @Override
  public List<FullIdentifier> toAbsSyn() {
    return cpsProgParam.toAbsSyn();
  }
}
