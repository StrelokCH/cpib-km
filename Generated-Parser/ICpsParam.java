package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;
interface ICpsParam
{
    IAbsSyn.IExpr toAbsSyn();
    void print(String indent);
}
