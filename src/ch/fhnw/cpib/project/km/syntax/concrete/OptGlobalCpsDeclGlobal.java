package ch.fhnw.cpib.project.km.syntax.concrete;

import java.util.List;

import ch.fhnw.cpib.project.km.syntax.abst.FullIdentifier;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class OptGlobalCpsDeclGlobal implements IOptGlobalCpsDecl {
  private final Global global;
  private final ICpsDecl cpsDecl;

  public OptGlobalCpsDeclGlobal(final Global global, final ICpsDecl cpsDecl) {
    this.global = global;
    this.cpsDecl = cpsDecl;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(indent + "    T " + global.toString());
    cpsDecl.print(indent + "    ");
  }

  @Override
  public List<ch.fhnw.cpib.project.km.syntax.abst.IDecl> toAbsSyn() {
	  return cpsDecl.toAbsSyn();
  }
}
