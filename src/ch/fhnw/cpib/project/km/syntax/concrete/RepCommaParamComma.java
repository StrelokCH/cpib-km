package ch.fhnw.cpib.project.km.syntax.concrete;

import java.util.List;

import ch.fhnw.cpib.project.km.syntax.abst.FullIdentifier;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class RepCommaParamComma implements IRepCommaParam {
	private final Comma comma;
	private final ICpsParam cpsParam;

	public RepCommaParamComma(final Comma comma, final ICpsParam cpsParam) {
		this.comma = comma;
		this.cpsParam = cpsParam;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		System.out.println(indent + "    T " + comma.toString());
		cpsParam.print(indent + "    ");
	}

	@Override
	public List<FullIdentifier> toAbsSyn() {
		return cpsParam.toAbsSyn();
	}
}
