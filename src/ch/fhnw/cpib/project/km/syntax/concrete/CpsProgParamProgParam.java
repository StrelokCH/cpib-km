package ch.fhnw.cpib.project.km.syntax.concrete;

import java.util.List;

import ch.fhnw.cpib.project.km.syntax.abst.FullIdentifier;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class CpsProgParamProgParam implements ICpsProgParam {
  private final IProgParam progParam;
  private final IRepCommaProgParam repCommaProgParam;

  public CpsProgParamProgParam(
      final IProgParam progParam, final IRepCommaProgParam repCommaProgParam) {
    this.progParam = progParam;
    this.repCommaProgParam = repCommaProgParam;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    progParam.print(indent + "    ");
    repCommaProgParam.print(indent + "    ");
  }

  @Override
  public List<FullIdentifier> toAbsSyn() {
	  List<FullIdentifier> list = repCommaProgParam.toAbsSyn();
	  list.add(0, progParam.toAbsSyn());
	  return list;
  }
}
