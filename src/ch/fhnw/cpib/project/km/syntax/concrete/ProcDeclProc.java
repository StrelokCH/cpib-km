package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class ProcDeclProc implements IProcDecl {
  private final Procedure proc;
  private final Identifier ident;
  private final IParamList paramList;
  private final IGlobalGlobImps globalGlobImps;
  private final IOptLocalCpsStoDecl optLocalCpsStoDecl;
  private final Do aDo;
  private final ICpsCmd cpsCmd;
  private final EndProcedure endproc;

  public ProcDeclProc(
      final Procedure proc,
      final Identifier ident,
      final IParamList paramList,
      final IGlobalGlobImps globalGlobImps,
      final IOptLocalCpsStoDecl optLocalCpsStoDecl,
      final Do aDo,
      final ICpsCmd cpsCmd,
      final EndProcedure endproc) {
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
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(indent + "    T " + proc.toString());
    System.out.println(indent + "    T " + ident.toString());
    paramList.print(indent + "    ");
    globalGlobImps.print(indent + "    ");
    optLocalCpsStoDecl.print(indent + "    ");
    System.out.println(indent + "    T " + aDo.toString());
    cpsCmd.print(indent + "    ");
    System.out.println(indent + "    T " + endproc.toString());
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
