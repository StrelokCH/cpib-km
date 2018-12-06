package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.syntax.abst.FullIdentifier;
import ch.fhnw.cpib.project.km.syntax.concrete.IAbsSyn.IExpr;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public interface ITypedIdent
{
	FullIdentifier toAbsSyn();
	FullIdentifier toAbsSyn(Flowmode flowmode, Mechmode mechmode, Changemode changemode);
    void print(String indent);
}
