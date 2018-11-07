package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.*;
interface IRepMultoprFactor
{
    IAbsSyn.IExpr toAbsSyn();
    void print(String indent);
}
