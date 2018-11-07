package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;
interface IGlobalGlobImps
{
    IAbsSyn.IExpr toAbsSyn();
    void print(String indent);
}
