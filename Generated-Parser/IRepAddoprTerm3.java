package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

interface IRepAddoprTerm3
{
    IAbsSyn.IExpr toAbsSyn();
    void print(String indent);
}
