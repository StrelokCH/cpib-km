package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;
interface IMonadicOpr
{
    IAbsSyn.IExpr toAbsSyn();
    void print(String indent);
}
