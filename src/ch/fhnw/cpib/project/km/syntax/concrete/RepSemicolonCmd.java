package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;
import ch.fhnw.cpib.project.km.syntax.abst.*;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.cpib.project.km.syntax.abst.IExpression;

public class RepSemicolonCmd implements IRepSemicolonCmd {
	public RepSemicolonCmd() {}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
	}

	@Override
	public List<ICommand> toAbsSyn() {
		return new ArrayList<ICommand>();
	}
}
