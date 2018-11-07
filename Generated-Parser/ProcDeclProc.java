package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class ProcDeclProc implements IProcDecl {
  private final Proc proc;
  private final Ident ident;
  private final IParamList paramList;
  private final IGlobalGlobImps globalGlobImps;
  private final IOptLocalCpsStoDecl optLocalCpsStoDecl;
  private final ADo aDo;
  private final ICpsCmd cpsCmd;
  private final Endproc endproc;

  private ProcDeclProc(
      final Proc proc,
      final Ident ident,
      final IParamList paramList,
      final IGlobalGlobImps globalGlobImps,
      final IOptLocalCpsStoDecl optLocalCpsStoDecl,
      final ADo aDo,
      final ICpsCmd cpsCmd,
      final Endproc endproc) {
    this.proc = proc;
    this.ident = ident;
    this.paramList = paramList;
    this.globalGlobImps = globalGlobImps;
    this.optLocalCpsStoDecl = optLocalCpsStoDecl;
    this.aDo = aDo;
    this.cpsCmd = cpsCmd;
    this.endproc = endproc;
  }

  public void print(String indent) {
    System.out.println(indent + "ProcDeclProc");
    proc.print(indent + "    ");
    ident.print(indent + "    ");
    paramList.print(indent + "    ");
    globalGlobImps.print(indent + "    ");
    optLocalCpsStoDecl.print(indent + "    ");
    aDo.print(indent + "    ");
    cpsCmd.print(indent + "    ");
    endproc.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
