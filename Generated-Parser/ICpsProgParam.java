package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;
interface ICpsProgParam
{
    IAbsSyn.IExpr toAbsSyn();
    void print(String indent);
}
