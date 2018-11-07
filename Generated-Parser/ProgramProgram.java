package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;

class ProgramProgram implements IProgram {
  private final Program program;
  private final Ident ident;
  private final IProgParamList progParamList;
  private final IOptGlobalCpsDecl optGlobalCpsDecl;
  private final ADo aDo;
  private final ICpsCmd cpsCmd;
  private final Endprogram endprogram;

  private ProgramProgram(
      final Program program,
      final Ident ident,
      final IProgParamList progParamList,
      final IOptGlobalCpsDecl optGlobalCpsDecl,
      final ADo aDo,
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
    System.out.println(indent + "ProgramProgram");
    program.print(indent + "    ");
    ident.print(indent + "    ");
    progParamList.print(indent + "    ");
    optGlobalCpsDecl.print(indent + "    ");
    aDo.print(indent + "    ");
    cpsCmd.print(indent + "    ");
    endprogram.print(indent + "    ");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return null;
  }
}
