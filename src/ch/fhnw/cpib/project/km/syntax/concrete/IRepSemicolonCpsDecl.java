package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public interface IRepSemicolonCpsDecl
{
    IAbsSyn.IExpr toAbsSyn();
    void print(String indent);
}
