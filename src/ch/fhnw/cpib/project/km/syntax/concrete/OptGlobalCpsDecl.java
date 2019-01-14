package ch.fhnw.cpib.project.km.syntax.concrete;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.cpib.project.km.syntax.abst.FullIdentifier;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class OptGlobalCpsDecl implements IOptGlobalCpsDecl {
  public OptGlobalCpsDecl() {}

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
  }

  @Override
  public List<ch.fhnw.cpib.project.km.syntax.abst.IDecl> toAbsSyn() {
    return new ArrayList<ch.fhnw.cpib.project.km.syntax.abst.IDecl>();
  }
}
