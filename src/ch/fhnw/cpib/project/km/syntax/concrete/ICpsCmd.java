package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.syntax.abst.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

import java.util.List;

import ch.fhnw.cpib.project.km.syntax.abst.IExpression;

public interface ICpsCmd
{
	ICommand toAbsSyn();
	void print(String indent);
}
