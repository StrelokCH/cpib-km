package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;
interface IOptCpsExpr
{
    IAbsSyn.IExpr toAbsSyn();
    void print(String indent);
}
