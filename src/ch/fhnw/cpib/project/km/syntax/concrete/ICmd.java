package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;
import ch.fhnw.cpib.project.km.syntax.abst.IExpression;
import java.util.List;

public interface ICmd
{
    List<IExpression> toAbsSyn();
    void print(String indent);
}
