package ch.fhnw.cpib.project.km.syntax.concrete;

import java.util.List;

import ch.fhnw.cpib.project.km.syntax.abst.IExpression;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public interface IRepCommaExpr
{
	List<IExpression> toAbsSyn();
    void print(String indent);
}
