package ch.fhnw.cpib.project.km.syntax.concrete;

import java.util.List;

import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class RepCommaIdentComma implements IRepCommaIdent {
	private final Comma comma;
	private final IIdents idents;

	public RepCommaIdentComma(final Comma comma, final IIdents idents) {
		this.comma = comma;
		this.idents = idents;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		System.out.println(indent + "    T " + comma.toString());
		idents.print(indent + "    ");
	}

	@Override
	public List<Identifier> toAbsSyn() {
		return idents.toAbsSyn();
	}
}
