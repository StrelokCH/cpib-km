package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.syntax.abst.IExpression;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public interface ITerm2
{
    IExpression toAbsSyn();
	IExpression toAbsSyn(AddOperator addopr, IExpression expr);
    void print(String indent);
}
