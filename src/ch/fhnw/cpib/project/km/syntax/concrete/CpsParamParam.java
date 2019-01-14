package ch.fhnw.cpib.project.km.syntax.concrete;

import java.util.List;

import ch.fhnw.cpib.project.km.syntax.abst.FullIdentifier;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class CpsParamParam implements ICpsParam {
	private final IParam param;
	private final IRepCommaParam repCommaParam;

	public CpsParamParam(final IParam param, final IRepCommaParam repCommaParam) {
		this.param = param;
		this.repCommaParam = repCommaParam;
	}

	public void print(String indent) {
		System.out.println(indent + this.getClass().getSimpleName());
		param.print(indent + "    ");
		repCommaParam.print(indent + "    ");
	}

	@Override
	public List<FullIdentifier> toAbsSyn() {
		List<FullIdentifier> ret = repCommaParam.toAbsSyn();
		ret.add(0, param.toAbsSyn());
		return ret;
	}
}
