package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;
interface ICpsCmd
{
    IAbsSyn.IExpr toAbsSyn();
    void print(String indent);
}
