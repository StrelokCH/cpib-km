package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

class ProgramProgram implements IProgram {
  private final Program program;
  private final Ident ident;
  private final IProgParamList progParamList;
  private final IOptGlobalCpsDecl optGlobalCpsDecl;
  private final Do aDo;
  private final ICpsCmd cpsCmd;
  private final Endprogram endprogram;

  private ProgramProgram(
      final Program program,
      final Ident ident,
      final IProgParamList progParamList,
      final IOptGlobalCpsDecl optGlobalCpsDecl,
      final Do aDo,
      final ICpsCmd cpsCmd,
      final Endprogram endprogram) {
    this.program = program;
    this.ident = ident;
    this.progParamList = progParamList;
    this.optGlobalCpsDecl = optGlobalCpsDecl;
    this.aDo = aDo;
    this.cpsCmd = cpsCmd;
    this.endprogram = endprogram;
  }

  public void print(String indent) {
    System.out.println(indent + this.getClass().getSimpleName());
    System.out.println(ident + "    T " + program.toString());
    System.out.println(ident + "    T " + ident.toString());
    progParamList.print(indent + "    ");
    optGlobalCpsDecl.print(indent + "    ");
    System.out.println(ident + "    T " + aDo.toString());
    cpsCmd.print(indent + "    ");
    System.out.println(ident + "    T " + endprogram.toString());
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
