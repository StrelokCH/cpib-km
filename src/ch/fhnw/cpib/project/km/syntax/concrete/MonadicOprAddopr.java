package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class MonadicOprAddopr implements IMonadicOpr {
	private final AddOperator addopr;

	public MonadicOprAddopr(final AddOperator addopr) {
		this.addopr = addopr;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		System.out.println(indent + "    T " + addopr.toString());
	}

	@Override
	public Operator toAbsSyn() {
		return addopr;
	}
}
