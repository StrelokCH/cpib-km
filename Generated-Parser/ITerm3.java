package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;
interface ITerm3
{
    IAbsSyn.IExpr toAbsSyn();
    void print(String indent);
}
