package ch.fhnw.cpib.project.km.syntax.concrete;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.cpib.project.km.syntax.abst.FullIdentifier;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class RepCommaGlobImps implements IRepCommaGlobImps {
	public RepCommaGlobImps() {
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
	}

	@Override
	public List<FullIdentifier> toAbsSyn() {
		return new ArrayList<FullIdentifier>();
	}
}
