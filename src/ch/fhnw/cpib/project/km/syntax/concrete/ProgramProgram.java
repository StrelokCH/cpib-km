package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class ProgramProgram implements IProgram {
  private final Program program;
  private final Identifier ident;
  private final IProgParamList progParamList;
  private final IOptGlobalCpsDecl optGlobalCpsDecl;
  private final Do aDo;
  private final ICpsCmd cpsCmd;
  private final EndProgram endprogram;

  private ProgramProgram(
      final Program program,
      final Identifier ident,
      final IProgParamList progParamList,
      final IOptGlobalCpsDecl optGlobalCpsDecl,
      final Do aDo,
      final ICpsCmd cpsCmd,
      final EndProgram endprogram) {
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
