package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;
interface IExpr
{
    IAbsSyn.IExpr toAbsSyn();
    void print(String indent);
}
