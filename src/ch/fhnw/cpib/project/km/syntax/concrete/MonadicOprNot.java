package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class MonadicOprNot implements IMonadicOpr {
	private final NotOperator not;

	public MonadicOprNot(final NotOperator not) {
		this.not = not;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		System.out.println(indent + "    T " + not.toString());
	}

	@Override
	public Operator toAbsSyn() {
		return not;
	}
}
