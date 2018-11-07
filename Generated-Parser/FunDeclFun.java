package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class FunDeclFun implements IFunDecl {
  private final Fun fun;
  private final Ident ident;
  private final IParamList paramList;
  private final Returns returns;
  private final IStoDecl stoDecl;
  private final IGlobalGlobImps globalGlobImps;
  private final IOptLocalCpsStoDecl optLocalCpsStoDecl;
  private final ADo aDo;
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
      final ADo aDo,
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
    System.out.println(indent + "FunDeclFun");
    fun.print(indent + "    ");
    ident.print(indent + "    ");
    paramList.print(indent + "    ");
    returns.print(indent + "    ");
    stoDecl.print(indent + "    ");
    globalGlobImps.print(indent + "    ");
    optLocalCpsStoDecl.print(indent + "    ");
    aDo.print(indent + "    ");
    cpsCmd.print(indent + "    ");
    endfun.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
