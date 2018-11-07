package ch.fhnw.cpib.project.km.parser.generated;

import ch.fhnw.cpib.project.km.token.*;
interface IProcDecl
{
    IAbsSyn.IExpr toAbsSyn();
    void print(String indent);
}
