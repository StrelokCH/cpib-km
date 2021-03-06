package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class FunDeclFun implements IFunDecl {
  private final Fun fun;
  private final Ident ident;
  private final IParamList paramList;
  private final Returns returns;
  private final IStoDecl stoDecl;
  private final IGlobalGlobImps globalGlobImps;
  private final IOptLocalCpsStoDecl optLocalCpsStoDecl;
  private final Do aDo;
  private final ICpsCmd cpsCmd;
  private final Endfun endfun;

  private FunDeclFun(
      final Fun fun,
      final Ident ident,
      final IParamList paramList,
      final Returns returns,
      final IStoDecl stoDecl,
      final IGlobalGlobImps globalGlobImps,
      final IOptLocalCpsStoDecl optLocalCpsStoDecl,
      final Do aDo,
      final ICpsCmd cpsCmd,
      final Endfun endfun) {
    this.fun = fun;
    this.ident = ident;
    this.paramList = paramList;
    this.returns = returns;
    this.stoDecl = stoDecl;
    this.globalGlobImps = globalGlobImps;
    this.optLocalCpsStoDecl = optLocalCpsStoDecl;
    this.aDo = aDo;
    this.cpsCmd = cpsCmd;
    this.endfun = endfun;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(ident + "    T " + fun.toString());
    System.out.println(ident + "    T " + ident.toString());
    paramList.print(indent + "    ");
    System.out.println(ident + "    T " + returns.toString());
    stoDecl.print(indent + "    ");
    globalGlobImps.print(indent + "    ");
    optLocalCpsStoDecl.print(indent + "    ");
    System.out.println(ident + "    T " + aDo.toString());
    cpsCmd.print(indent + "    ");
    System.out.println(ident + "    T " + endfun.toString());
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
