package ch.fhnw.cpib.project.km.parser;

import ch.fhnw.cpib.project.km.token.BaseToken;
import ch.fhnw.cpib.project.km.token.ITokenList;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

import ch.fhnw.cpib.project.km.exceptions.*;
import ch.fhnw.cpib.project.km.syntax.concrete.*;

public class Parser {

	private boolean verbose;
	private ITokenList tokenList;
	private BaseToken terminal;

	public Parser(ITokenList tokenList) {
		this.tokenList = tokenList;
		this.tokenList.reset();
		// precondition: token list contains at least the SENTINEL
		terminal = tokenList.nextToken();
	}

	public IProgram parse() throws GrammarException {
		return parse(false);
	}

	public IProgram parse(boolean verbose) throws GrammarException {
		this.verbose = verbose;
		// parsing the start symbol ...
		IProgram program = program();
		// ... and then consuming the SENTINEL
		consume(Sentinel.class);
		return program;
	}

	private BaseToken consume(Class<?> expectedTerminal) throws GrammarException {
		if (expectedTerminal.isInstance(terminal)) {
			BaseToken consumedToken = terminal;
			if (!Sentinel.class.isInstance(terminal)) {
				terminal = tokenList.nextToken();
			}
			return consumedToken;
		} else {
			throw new GrammarException(
					"terminal expected: " + expectedTerminal.getName() + ", terminal found: " + terminal);
		}
	}

	private IProgram program() throws GrammarException {
		print("\"Program ::= ");
		if (terminal instanceof Program) {
			println("PROGRAM IDENT <progParamList> <optGlobalCpsDecl> DO <cpsCmd> ENDPROGRAM\"");
			Program program = ((Program) consume(Program.class));
			Identifier ident = ((Identifier) consume(Identifier.class));
			IProgParamList progParamList = progParamList();
			IOptGlobalCpsDecl optGlobalCpsDecl = optGlobalCpsDecl();
			Do aDo = ((Do) consume(Do.class));
			ICpsCmd cpsCmd = cpsCmd();
			EndProgram endprogram = ((EndProgram) consume(EndProgram.class));
			return new ProgramProgram(program, ident, progParamList, optGlobalCpsDecl, aDo, cpsCmd, endprogram);
		}
		throw new GrammarException("Program");
	}

	private IOptGlobalCpsDecl optGlobalCpsDecl() throws GrammarException {
		print("\"OptGlobalCpsDecl ::= ");
		if (terminal instanceof Global) {
			println("GLOBAL <cpsDecl>\"");
			Global global = ((Global) consume(Global.class));
			ICpsDecl cpsDecl = cpsDecl();
			return new OptGlobalCpsDeclGlobal(global, cpsDecl);
		}
		if (terminal instanceof Do) {
			println("\"");
			return new OptGlobalCpsDecl();
		}
		throw new GrammarException("OptGlobalCpsDecl");
	}

	private IDecl decl() throws GrammarException {
		print("\"Decl ::= ");
		if (terminal instanceof Identifier || terminal instanceof Changemode) {
			println("<stoDecl>\"");
			IStoDecl stoDecl = stoDecl();
			return new DeclStoDecl(stoDecl);
		}
		if (terminal instanceof Function) {
			println("<funDecl>\"");
			IFunDecl funDecl = funDecl();
			return new DeclFunDecl(funDecl);
		}
		if (terminal instanceof Procedure) {
			println("<procDecl>\"");
			IProcDecl procDecl = procDecl();
			return new DeclProcDecl(procDecl);
		}
		throw new GrammarException("Decl");
	}

	private IStoDecl stoDecl() throws GrammarException {
		print("\"StoDecl ::= ");
		if (terminal instanceof Changemode) {
			println("CHANGEMODE <typedIdent>\"");
			Changemode changemode = ((Changemode) consume(Changemode.class));
			ITypedIdent typedIdent = typedIdent();
			return new StoDeclChangemode(changemode, typedIdent);
		}
		if (terminal instanceof Identifier) {
			println("<typedIdent>\"");
			ITypedIdent typedIdent = typedIdent();
			return new StoDeclTypedIdent(typedIdent);
		}
		throw new GrammarException("StoDecl");
	}

	private IFunDecl funDecl() throws GrammarException {
		print("\"FunDecl ::= ");
		if (terminal instanceof Function) {
			println("FUN IDENT <paramList> RETURNS <stoDecl> <globalGlobImps> <optLocalCpsStoDecl> DO <cpsCmd> ENDFUN\"");
			Function fun = ((Function) consume(Function.class));
			Identifier ident = ((Identifier) consume(Identifier.class));
			IParamList paramList = paramList();
			Returns returns = ((Returns) consume(Returns.class));
			IStoDecl stoDecl = stoDecl();
			IGlobalGlobImps globalGlobImps = globalGlobImps();
			IOptLocalCpsStoDecl optLocalCpsStoDecl = optLocalCpsStoDecl();
			Do aDo = ((Do) consume(Do.class));
			ICpsCmd cpsCmd = cpsCmd();
			EndFunction endfun = ((EndFunction) consume(EndFunction.class));
			return new FunDeclFun(fun, ident, paramList, returns, stoDecl, globalGlobImps, optLocalCpsStoDecl, aDo,
					cpsCmd, endfun);
		}
		throw new GrammarException("FunDecl");
	}

	private IGlobalGlobImps globalGlobImps() throws GrammarException {
		print("\"GlobalGlobImps ::= ");
		if (terminal instanceof Global) {
			println("GLOBAL <globImps>\"");
			Global global = ((Global) consume(Global.class));
			IGlobImps globImps = globImps();
			return new GlobalGlobImpsGlobal(global, globImps);
		}
		if (terminal instanceof Do || terminal instanceof Local) {
			println("\"");
			return new GlobalGlobImps();
		}
		throw new GrammarException("GlobalGlobImps");
	}

	private IOptLocalCpsStoDecl optLocalCpsStoDecl() throws GrammarException {
		print("\"OptLocalCpsStoDecl ::= ");
		if (terminal instanceof Local) {
			println("LOCAL <cpsStoDecl>\"");
			Local local = ((Local) consume(Local.class));
			ICpsStoDecl cpsStoDecl = cpsStoDecl();
			return new OptLocalCpsStoDeclLocal(local, cpsStoDecl);
		}
		if (terminal instanceof Do) {
			println("\"");
			return new OptLocalCpsStoDecl();
		}
		throw new GrammarException("OptLocalCpsStoDecl");
	}

	private IProcDecl procDecl() throws GrammarException {
		print("\"ProcDecl ::= ");
		if (terminal instanceof Procedure) {
			println("PROC IDENT <paramList> <globalGlobImps> <optLocalCpsStoDecl> DO <cpsCmd> ENDPROC\"");
			Procedure proc = ((Procedure) consume(Procedure.class));
			Identifier ident = ((Identifier) consume(Identifier.class));
			IParamList paramList = paramList();
			IGlobalGlobImps globalGlobImps = globalGlobImps();
			IOptLocalCpsStoDecl optLocalCpsStoDecl = optLocalCpsStoDecl();
			Do aDo = ((Do) consume(Do.class));
			ICpsCmd cpsCmd = cpsCmd();
			EndProcedure endproc = ((EndProcedure) consume(EndProcedure.class));
			return new ProcDeclProc(proc, ident, paramList, globalGlobImps, optLocalCpsStoDecl, aDo, cpsCmd, endproc);
		}
		throw new GrammarException("ProcDecl");
	}

	private IGlobImps globImps() throws GrammarException {
		print("\"GlobImps ::= ");
		if (terminal instanceof Identifier || terminal instanceof Changemode || terminal instanceof Flowmode) {
			println("<globImp> <repCommaGlobImps>\"");
			IGlobImp globImp = globImp();
			IRepCommaGlobImps repCommaGlobImps = repCommaGlobImps();
			return new GlobImpsGlobImp(globImp, repCommaGlobImps);
		}
		throw new GrammarException("GlobImps");
	}

	private IRepCommaGlobImps repCommaGlobImps() throws GrammarException {
		print("\"RepCommaGlobImps ::= ");
		if (terminal instanceof Comma) {
			println("COMMA <globImp> <repCommaGlobImps>\"");
			Comma comma = ((Comma) consume(Comma.class));
			IGlobImp globImp = globImp();
			IRepCommaGlobImps repCommaGlobImps = repCommaGlobImps();
			return new RepCommaGlobImpsComma(comma, globImp, repCommaGlobImps);
		}
		if (terminal instanceof Do || terminal instanceof Local) {
			println("\"");
			return new RepCommaGlobImps();
		}
		throw new GrammarException("RepCommaGlobImps");
	}

	private IGlobImp globImp() throws GrammarException {
		print("\"GlobImp ::= ");
		if (terminal instanceof Identifier || terminal instanceof Changemode || terminal instanceof Flowmode) {
			println("<optFlowmode> <optChangemode> IDENT\"");
			IOptFlowmode optFlowmode = optFlowmode();
			IOptChangemode optChangemode = optChangemode();
			Identifier ident = ((Identifier) consume(Identifier.class));
			return new GlobImpOptFlowmode(optFlowmode, optChangemode, ident);
		}
		throw new GrammarException("GlobImp");
	}

	private IOptFlowmode optFlowmode() throws GrammarException {
		print("\"OptFlowmode ::= ");
		if (terminal instanceof Flowmode) {
			println("FLOWMODE\"");
			Flowmode flowmode = ((Flowmode) consume(Flowmode.class));
			return new OptFlowmodeFlowmode(flowmode);
		}
		if (terminal instanceof Mechmode || terminal instanceof Identifier || terminal instanceof Changemode) {
			println("\"");
			return new OptFlowmode();
		}
		throw new GrammarException("OptFlowmode");
	}

	private IOptChangemode optChangemode() throws GrammarException {
		print("\"OptChangemode ::= ");
		if (terminal instanceof Changemode) {
			println("CHANGEMODE\"");
			Changemode changemode = ((Changemode) consume(Changemode.class));
			return new OptChangemodeChangemode(changemode);
		}
		if (terminal instanceof Identifier) {
			println("\"");
			return new OptChangemode();
		}
		throw new GrammarException("OptChangemode");
	}

	private IOptMechmode optMechmode() throws GrammarException {
		print("\"OptMechmode ::= ");
		if (terminal instanceof Mechmode) {
			println("MECHMODE\"");
			Mechmode mechmode = ((Mechmode) consume(Mechmode.class));
			return new OptMechmodeMechmode(mechmode);
		}
		if (terminal instanceof Identifier || terminal instanceof Changemode) {
			println("\"");
			return new OptMechmode();
		}
		throw new GrammarException("OptMechmode");
	}

	private ICpsDecl cpsDecl() throws GrammarException {
		print("\"CpsDecl ::= ");
		if (terminal instanceof Procedure || terminal instanceof Function || terminal instanceof Identifier
				|| terminal instanceof Changemode) {
			println("<decl> <repSemicolonCpsDecl>\"");
			IDecl decl = decl();
			IRepSemicolonCpsDecl repSemicolonCpsDecl = repSemicolonCpsDecl();
			return new CpsDeclDecl(decl, repSemicolonCpsDecl);
		}
		throw new GrammarException("CpsDecl");
	}

	private IRepSemicolonCpsDecl repSemicolonCpsDecl() throws GrammarException {
		print("\"RepSemicolonCpsDecl ::= ");
		if (terminal instanceof Semicolon) {
			println("SEMICOLON <cpsDecl>\"");
			Semicolon semicolon = ((Semicolon) consume(Semicolon.class));
			ICpsDecl cpsDecl = cpsDecl();
			return new RepSemicolonCpsDeclSemicolon(semicolon, cpsDecl);
		}
		if (terminal instanceof Do) {
			println("\"");
			return new RepSemicolonCpsDecl();
		}
		throw new GrammarException("RepSemicolonCpsDecl");
	}

	private ICpsStoDecl cpsStoDecl() throws GrammarException {
		print("\"CpsStoDecl ::= ");
		if (terminal instanceof Identifier || terminal instanceof Changemode) {
			println("<stoDecl> <repSemicolonCpsStoDecl>\"");
			IStoDecl stoDecl = stoDecl();
			IRepSemicolonCpsStoDecl repSemicolonCpsStoDecl = repSemicolonCpsStoDecl();
			return new CpsStoDeclStoDecl(stoDecl, repSemicolonCpsStoDecl);
		}
		throw new GrammarException("CpsStoDecl");
	}

	private IRepSemicolonCpsStoDecl repSemicolonCpsStoDecl() throws GrammarException {
		print("\"RepSemicolonCpsStoDecl ::= ");
		if (terminal instanceof Semicolon) {
			println("SEMICOLON <cpsStoDecl>\"");
			Semicolon semicolon = ((Semicolon) consume(Semicolon.class));
			ICpsStoDecl cpsStoDecl = cpsStoDecl();
			return new RepSemicolonCpsStoDeclSemicolon(semicolon, cpsStoDecl);
		}
		if (terminal instanceof Do) {
			println("\"");
			return new RepSemicolonCpsStoDecl();
		}
		throw new GrammarException("RepSemicolonCpsStoDecl");
	}

	private IProgParamList progParamList() throws GrammarException {
		print("\"ProgParamList ::= ");
		if (terminal instanceof LeftParenthesis) {
			println("LPAREN <optCpsProgParam> RPAREN\"");
			LeftParenthesis lparen = ((LeftParenthesis) consume(LeftParenthesis.class));
			IOptCpsProgParam optCpsProgParam = optCpsProgParam();
			RightParenthesis rparen = ((RightParenthesis) consume(RightParenthesis.class));
			return new ProgParamListLparen(lparen, optCpsProgParam, rparen);
		}
		throw new GrammarException("ProgParamList");
	}

	private IOptCpsProgParam optCpsProgParam() throws GrammarException {
		print("\"OptCpsProgParam ::= ");
		if (terminal instanceof Identifier || terminal instanceof Changemode || terminal instanceof Flowmode) {
			println("<cpsProgParam>\"");
			ICpsProgParam cpsProgParam = cpsProgParam();
			return new OptCpsProgParamCpsProgParam(cpsProgParam);
		}
		if (terminal instanceof RightParenthesis) {
			println("\"");
			return new OptCpsProgParam();
		}
		throw new GrammarException("OptCpsProgParam");
	}

	private ICpsProgParam cpsProgParam() throws GrammarException {
		print("\"CpsProgParam ::= ");
		if (terminal instanceof Identifier || terminal instanceof Changemode || terminal instanceof Flowmode) {
			println("<progParam> <repCommaProgParam>\"");
			IProgParam progParam = progParam();
			IRepCommaProgParam repCommaProgParam = repCommaProgParam();
			return new CpsProgParamProgParam(progParam, repCommaProgParam);
		}
		throw new GrammarException("CpsProgParam");
	}

	private IRepCommaProgParam repCommaProgParam() throws GrammarException {
		print("\"RepCommaProgParam ::= ");
		if (terminal instanceof Comma) {
			println("COMMA <cpsProgParam>\"");
			Comma comma = ((Comma) consume(Comma.class));
			ICpsProgParam cpsProgParam = cpsProgParam();
			return new RepCommaProgParamComma(comma, cpsProgParam);
		}
		if (terminal instanceof RightParenthesis) {
			println("\"");
			return new RepCommaProgParam();
		}
		throw new GrammarException("RepCommaProgParam");
	}

	private IProgParam progParam() throws GrammarException {
		print("\"ProgParam ::= ");
		if (terminal instanceof Identifier || terminal instanceof Changemode || terminal instanceof Flowmode) {
			println("<optFlowmode> <optChangemode> <typedIdent>\"");
			IOptFlowmode optFlowmode = optFlowmode();
			IOptChangemode optChangemode = optChangemode();
			ITypedIdent typedIdent = typedIdent();
			return new ProgParamOptFlowmode(optFlowmode, optChangemode, typedIdent);
		}
		throw new GrammarException("ProgParam");
	}

	private IParamList paramList() throws GrammarException {
		print("\"ParamList ::= ");
		if (terminal instanceof LeftParenthesis) {
			println("LPAREN <optCpsParam> RPAREN\"");
			LeftParenthesis lparen = ((LeftParenthesis) consume(LeftParenthesis.class));
			IOptCpsParam optCpsParam = optCpsParam();
			RightParenthesis rparen = ((RightParenthesis) consume(RightParenthesis.class));
			return new ParamListLparen(lparen, optCpsParam, rparen);
		}
		throw new GrammarException("ParamList");
	}

	private IOptCpsParam optCpsParam() throws GrammarException {
		print("\"OptCpsParam ::= ");
		if (terminal instanceof Identifier || terminal instanceof Changemode || terminal instanceof Mechmode
				|| terminal instanceof Flowmode) {
			println("<cpsParam>\"");
			ICpsParam cpsParam = cpsParam();
			return new OptCpsParamCpsParam(cpsParam);
		}
		if (terminal instanceof RightParenthesis) {
			println("\"");
			return new OptCpsParam();
		}
		throw new GrammarException("OptCpsParam");
	}

	private ICpsParam cpsParam() throws GrammarException {
		print("\"CpsParam ::= ");
		if (terminal instanceof Identifier || terminal instanceof Changemode || terminal instanceof Mechmode
				|| terminal instanceof Flowmode) {
			println("<param> <repCommaParam>\"");
			IParam param = param();
			IRepCommaParam repCommaParam = repCommaParam();
			return new CpsParamParam(param, repCommaParam);
		}
		throw new GrammarException("CpsParam");
	}

	private IRepCommaParam repCommaParam() throws GrammarException {
		print("\"RepCommaParam ::= ");
		if (terminal instanceof Comma) {
			println("COMMA <cpsParam>\"");
			Comma comma = ((Comma) consume(Comma.class));
			ICpsParam cpsParam = cpsParam();
			return new RepCommaParamComma(comma, cpsParam);
		}
		if (terminal instanceof RightParenthesis) {
			println("\"");
			return new RepCommaParam();
		}
		throw new GrammarException("RepCommaParam");
	}

	private IParam param() throws GrammarException {
		print("\"Param ::= ");
		if (terminal instanceof Identifier || terminal instanceof Changemode || terminal instanceof Mechmode
				|| terminal instanceof Flowmode) {
			println("<optFlowmode> <optMechmode> <optChangemode> <typedIdent>\"");
			IOptFlowmode optFlowmode = optFlowmode();
			IOptMechmode optMechmode = optMechmode();
			IOptChangemode optChangemode = optChangemode();
			ITypedIdent typedIdent = typedIdent();
			return new ParamOptFlowmode(optFlowmode, optMechmode, optChangemode, typedIdent);
		}
		throw new GrammarException("Param");
	}

	private ITypedIdent typedIdent() throws GrammarException {
		print("\"TypedIdent ::= ");
		if (terminal instanceof Identifier) {
			println("IDENT COLON ATOMTYPE\"");
			Identifier ident = ((Identifier) consume(Identifier.class));
			Colon colon = ((Colon) consume(Colon.class));
			Type atomtype = ((Type) consume(Type.class));
			return new TypedIdentIdent(ident, colon, atomtype);
		}
		throw new GrammarException("TypedIdent");
	}

	private ICmd cmd() throws GrammarException {
		print("\"Cmd ::= ");
		if (terminal instanceof Skip) {
			println("SKIP\"");
			Skip skip = ((Skip) consume(Skip.class));
			return new CmdSkip(skip);
		}
		if (terminal instanceof LeftParenthesis || terminal instanceof AddOperator
				|| terminal instanceof NotEqualsOperator || terminal instanceof Identifier
				|| terminal instanceof Literal) {
			println("<expr> BECOMES <expr>\"");
			IExpr expr = expr();
			Becomes becomes = ((Becomes) consume(Becomes.class));
			IExpr expr2 = expr();
			return new CmdExpr(expr, becomes, expr2);
		}
		if (terminal instanceof If) {
			println("IF <expr> THEN <cpsCmd> ELSE <cpsCmd> ENDIF\"");
			If aIf = ((If) consume(If.class));
			IExpr expr = expr();
			Then then = ((Then) consume(Then.class));
			ICpsCmd cpsCmd = cpsCmd();
			Else aElse = ((Else) consume(Else.class));
			ICpsCmd cpsCmd2 = cpsCmd();
			EndIf endif = ((EndIf) consume(EndIf.class));
			return new CmdIf(aIf, expr, then, cpsCmd, aElse, cpsCmd2, endif);
		}
		if (terminal instanceof While) {
			println("WHILE <expr> DO <cpsCmd> ENDWHILE\"");
			While aWhile = ((While) consume(While.class));
			IExpr expr = expr();
			Do aDo = ((Do) consume(Do.class));
			ICpsCmd cpsCmd = cpsCmd();
			EndWhile endwhile = ((EndWhile) consume(EndWhile.class));
			return new CmdWhile(aWhile, expr, aDo, cpsCmd, endwhile);
		}
		if (terminal instanceof Call) {
			println("CALL IDENT <exprList> <optGlobInits>\"");
			Call call = ((Call) consume(Call.class));
			Identifier ident = ((Identifier) consume(Identifier.class));
			IExprList exprList = exprList();
			IOptGlobInits optGlobInits = optGlobInits();
			return new CmdCall(call, ident, exprList, optGlobInits);
		}
		if (terminal instanceof DebugIn) {
			println("DEBUGIN <expr>\"");
			DebugIn debugin = ((DebugIn) consume(DebugIn.class));
			IExpr expr = expr();
			return new CmdDebugin(debugin, expr);
		}
		if (terminal instanceof DebugOut) {
			println("DEBUGOUT <expr>\"");
			DebugOut debugout = ((DebugOut) consume(DebugOut.class));
			IExpr expr = expr();
			return new CmdDebugout(debugout, expr);
		}
		throw new GrammarException("Cmd");
	}

	private IOptGlobInits optGlobInits() throws GrammarException {
		print("\"OptGlobInits ::= ");
		if (terminal instanceof Init) {
			println("<globInits>\"");
			IGlobInits globInits = globInits();
			return new OptGlobInitsGlobInits(globInits);
		}
		if (terminal instanceof EndWhile || terminal instanceof EndIf || terminal instanceof Else
				|| terminal instanceof EndProcedure || terminal instanceof EndFunction || terminal instanceof EndProgram
				|| terminal instanceof Semicolon) {
			println("\"");
			return new OptGlobInits();
		}
		throw new GrammarException("OptGlobInits");
	}

	private ICpsCmd cpsCmd() throws GrammarException {
		print("\"CpsCmd ::= ");
		if (terminal instanceof DebugOut || terminal instanceof DebugIn || terminal instanceof Call
				|| terminal instanceof While || terminal instanceof If || terminal instanceof LeftParenthesis
				|| terminal instanceof AddOperator || terminal instanceof NotEqualsOperator
				|| terminal instanceof Identifier || terminal instanceof Literal || terminal instanceof Skip) {
			println("<cmd> <repSemicolonCmd>\"");
			ICmd cmd = cmd();
			IRepSemicolonCmd repSemicolonCmd = repSemicolonCmd();
			return new CpsCmdCmd(cmd, repSemicolonCmd);
		}
		throw new GrammarException("CpsCmd");
	}

	private IRepSemicolonCmd repSemicolonCmd() throws GrammarException {
		print("\"RepSemicolonCmd ::= ");
		if (terminal instanceof Semicolon) {
			println("SEMICOLON <cpsCmd>\"");
			Semicolon semicolon = ((Semicolon) consume(Semicolon.class));
			ICpsCmd cpsCmd = cpsCmd();
			return new RepSemicolonCmdSemicolon(semicolon, cpsCmd);
		}
		if (terminal instanceof EndWhile || terminal instanceof EndIf || terminal instanceof Else
				|| terminal instanceof EndProcedure || terminal instanceof EndFunction
				|| terminal instanceof EndProgram) {
			println("\"");
			return new RepSemicolonCmd();
		}
		throw new GrammarException("RepSemicolonCmd");
	}

	private IGlobInits globInits() throws GrammarException {
		print("\"GlobInits ::= ");
		if (terminal instanceof Init) {
			println("INIT <idents>\"");
			Init init = ((Init) consume(Init.class));
			IIdents idents = idents();
			return new GlobInitsInit(init, idents);
		}
		throw new GrammarException("GlobInits");
	}

	private IIdents idents() throws GrammarException {
		print("\"Idents ::= ");
		if (terminal instanceof Identifier) {
			println("IDENT <repCommaIdent>\"");
			Identifier ident = ((Identifier) consume(Identifier.class));
			IRepCommaIdent repCommaIdent = repCommaIdent();
			return new IdentsIdent(ident, repCommaIdent);
		}
		throw new GrammarException("Idents");
	}

	private IRepCommaIdent repCommaIdent() throws GrammarException {
		print("\"RepCommaIdent ::= ");
		if (terminal instanceof Comma) {
			println("COMMA <idents>\"");
			Comma comma = ((Comma) consume(Comma.class));
			IIdents idents = idents();
			return new RepCommaIdentComma(comma, idents);
		}
		if (terminal instanceof EndWhile || terminal instanceof EndIf || terminal instanceof Else
				|| terminal instanceof EndProcedure || terminal instanceof EndFunction || terminal instanceof EndProgram
				|| terminal instanceof Semicolon) {
			println("\"");
			return new RepCommaIdent();
		}
		throw new GrammarException("RepCommaIdent");
	}

	private IExpr expr() throws GrammarException {
		print("\"Expr ::= ");
		if (terminal instanceof LeftParenthesis || terminal instanceof AddOperator
				|| terminal instanceof NotEqualsOperator || terminal instanceof Identifier
				|| terminal instanceof Literal) {
			println("<term1> <repBooloprExpr>\"");
			ITerm1 term1 = term1();
			IRepBooloprExpr repBooloprExpr = repBooloprExpr();
			return new ExprTerm1(term1, repBooloprExpr);
		}
		throw new GrammarException("Expr");
	}

	private IRepBooloprExpr repBooloprExpr() throws GrammarException {
		print("\"RepBooloprExpr ::= ");
		if (terminal instanceof BooleanOperator) {
			println("BOOLOPR <expr>\"");
			BooleanOperator boolopr = ((BooleanOperator) consume(BooleanOperator.class));
			IExpr expr = expr();
			return new RepBooloprExprBoolopr(boolopr, expr);
		}
		if (terminal instanceof Comma || terminal instanceof RightParenthesis || terminal instanceof Do
				|| terminal instanceof Then || terminal instanceof EndWhile || terminal instanceof EndIf
				|| terminal instanceof Else || terminal instanceof EndProcedure || terminal instanceof EndFunction
				|| terminal instanceof EndProgram || terminal instanceof Semicolon || terminal instanceof Becomes) {
			println("\"");
			return new RepBooloprExpr();
		}
		throw new GrammarException("RepBooloprExpr");
	}

	private ITerm1 term1() throws GrammarException {
		print("\"Term1 ::= ");
		if (terminal instanceof LeftParenthesis || terminal instanceof AddOperator
				|| terminal instanceof NotEqualsOperator || terminal instanceof Identifier
				|| terminal instanceof Literal) {
			println("<term2> <optReloprTerm2>\"");
			ITerm2 term2 = term2();
			IOptReloprTerm2 optReloprTerm2 = optReloprTerm2();
			return new Term1Term2(term2, optReloprTerm2);
		}
		throw new GrammarException("Term1");
	}

	private IOptReloprTerm2 optReloprTerm2() throws GrammarException {
		print("\"OptReloprTerm2 ::= ");
		if (terminal instanceof RelationalOperator) {
			println("RELOPR <term2>\"");
			RelationalOperator relopr = ((RelationalOperator) consume(RelationalOperator.class));
			ITerm2 term2 = term2();
			return new OptReloprTerm2Relopr(relopr, term2);
		}
		if (terminal instanceof Comma || terminal instanceof RightParenthesis || terminal instanceof Do
				|| terminal instanceof Then || terminal instanceof EndWhile || terminal instanceof EndIf
				|| terminal instanceof Else || terminal instanceof EndProcedure || terminal instanceof EndFunction
				|| terminal instanceof EndProgram || terminal instanceof Semicolon || terminal instanceof Becomes
				|| terminal instanceof BooleanOperator) {
			println("\"");
			return new OptReloprTerm2();
		}
		throw new GrammarException("OptReloprTerm2");
	}

	private ITerm2 term2() throws GrammarException {
		print("\"Term2 ::= ");
		if (terminal instanceof LeftParenthesis || terminal instanceof AddOperator
				|| terminal instanceof NotEqualsOperator || terminal instanceof Identifier
				|| terminal instanceof Literal) {
			println("<term3> <repAddoprTerm3>\"");
			ITerm3 term3 = term3();
			IRepAddoprTerm3 repAddoprTerm3 = repAddoprTerm3();
			return new Term2Term3(term3, repAddoprTerm3);
		}
		throw new GrammarException("Term2");
	}

	private IRepAddoprTerm3 repAddoprTerm3() throws GrammarException {
		print("\"RepAddoprTerm3 ::= ");
		if (terminal instanceof AddOperator) {
			println("ADDOPR <term2>\"");
			AddOperator addopr = ((AddOperator) consume(AddOperator.class));
			ITerm2 term2 = term2();
			return new RepAddoprTerm3Addopr(addopr, term2);
		}
		if (terminal instanceof Comma || terminal instanceof RightParenthesis || terminal instanceof Do
				|| terminal instanceof Then || terminal instanceof EndWhile || terminal instanceof EndIf
				|| terminal instanceof Else || terminal instanceof EndProcedure || terminal instanceof EndFunction
				|| terminal instanceof EndProgram || terminal instanceof Semicolon || terminal instanceof Becomes
				|| terminal instanceof BooleanOperator || terminal instanceof RelationalOperator) {
			println("\"");
			return new RepAddoprTerm3();
		}
		throw new GrammarException("RepAddoprTerm3");
	}

	private ITerm3 term3() throws GrammarException {
		print("\"Term3 ::= ");
		if (terminal instanceof LeftParenthesis || terminal instanceof AddOperator
				|| terminal instanceof NotEqualsOperator || terminal instanceof Identifier
				|| terminal instanceof Literal) {
			println("<factor> <repMultoprFactor>\"");
			IFactor factor = factor();
			IRepMultoprFactor repMultoprFactor = repMultoprFactor();
			return new Term3Factor(factor, repMultoprFactor);
		}
		throw new GrammarException("Term3");
	}

	private IRepMultoprFactor repMultoprFactor() throws GrammarException {
		print("\"RepMultoprFactor ::= ");
		if (terminal instanceof MultiplicationOperator) {
			println("MULTOPR <term3>\"");
			MultiplicationOperator multopr = ((MultiplicationOperator) consume(MultiplicationOperator.class));
			ITerm3 term3 = term3();
			return new RepMultoprFactorMultopr(multopr, term3);
		}
		if (terminal instanceof Comma || terminal instanceof RightParenthesis || terminal instanceof Do
				|| terminal instanceof Then || terminal instanceof EndWhile || terminal instanceof EndIf
				|| terminal instanceof Else || terminal instanceof EndProcedure || terminal instanceof EndFunction
				|| terminal instanceof EndProgram || terminal instanceof Semicolon || terminal instanceof Becomes
				|| terminal instanceof BooleanOperator || terminal instanceof RelationalOperator
				|| terminal instanceof AddOperator) {
			println("\"");
			return new RepMultoprFactor();
		}
		throw new GrammarException("RepMultoprFactor");
	}

	private IFactor factor() throws GrammarException {
		print("\"Factor ::= ");
		if (terminal instanceof Literal) {
			println("LITERAL\"");
			Literal literal = ((Literal) consume(Literal.class));
			return new FactorLiteral(literal);
		}
		if (terminal instanceof Identifier) {
			println("IDENT <ident1>\"");
			Identifier ident = ((Identifier) consume(Identifier.class));
			IIdent1 ident1 = ident1();
			return new FactorIdent(ident, ident1);
		}
		if (terminal instanceof AddOperator || terminal instanceof NotEqualsOperator) {
			println("<monadicOpr> <factor>\"");
			IMonadicOpr monadicOpr = monadicOpr();
			IFactor factor = factor();
			return new FactorMonadicOpr(monadicOpr, factor);
		}
		if (terminal instanceof LeftParenthesis) {
			println("LPAREN <expr> RPAREN\"");
			LeftParenthesis lparen = ((LeftParenthesis) consume(LeftParenthesis.class));
			IExpr expr = expr();
			RightParenthesis rparen = ((RightParenthesis) consume(RightParenthesis.class));
			return new FactorLparen(lparen, expr, rparen);
		}
		throw new GrammarException("Factor");
	}

	private IIdent1 ident1() throws GrammarException {
		print("\"Ident1 ::= ");
		if (terminal instanceof Init) {
			println("INIT\"");
			Init init = ((Init) consume(Init.class));
			return new Ident1Init(init);
		}
		if (terminal instanceof LeftParenthesis) {
			println("<exprList>\"");
			IExprList exprList = exprList();
			return new Ident1ExprList(exprList);
		}
		if (terminal instanceof Comma || terminal instanceof RightParenthesis || terminal instanceof Do
				|| terminal instanceof Then || terminal instanceof EndWhile || terminal instanceof EndIf
				|| terminal instanceof Else || terminal instanceof EndProcedure || terminal instanceof EndFunction
				|| terminal instanceof EndProgram || terminal instanceof Semicolon || terminal instanceof Becomes
				|| terminal instanceof BooleanOperator || terminal instanceof RelationalOperator
				|| terminal instanceof AddOperator || terminal instanceof MultiplicationOperator) {
			println("\"");
			return new Ident1();
		}
		throw new GrammarException("Ident1");
	}

	private IMonadicOpr monadicOpr() throws GrammarException {
		print("\"MonadicOpr ::= ");
		if (terminal instanceof NotEqualsOperator) {
			println("NOT\"");
			NotOperator not = ((NotOperator) consume(NotOperator.class));
			return new MonadicOprNot(not);
		}
		if (terminal instanceof AddOperator) {
			println("ADDOPR\"");
			AddOperator addopr = ((AddOperator) consume(AddOperator.class));
			return new MonadicOprAddopr(addopr);
		}
		throw new GrammarException("MonadicOpr");
	}

	private IExprList exprList() throws GrammarException {
		print("\"ExprList ::= ");
		if (terminal instanceof LeftParenthesis) {
			println("LPAREN <optCpsExpr> RPAREN\"");
			LeftParenthesis lparen = ((LeftParenthesis) consume(LeftParenthesis.class));
			IOptCpsExpr optCpsExpr = optCpsExpr();
			RightParenthesis rparen = ((RightParenthesis) consume(RightParenthesis.class));
			return new ExprListLparen(lparen, optCpsExpr, rparen);
		}
		throw new GrammarException("ExprList");
	}

	private IOptCpsExpr optCpsExpr() throws GrammarException {
		print("\"OptCpsExpr ::= ");
		if (terminal instanceof LeftParenthesis || terminal instanceof AddOperator
				|| terminal instanceof NotEqualsOperator || terminal instanceof Identifier
				|| terminal instanceof Literal) {
			println("<cpsExpr>\"");
			ICpsExpr cpsExpr = cpsExpr();
			return new OptCpsExprCpsExpr(cpsExpr);
		}
		if (terminal instanceof RightParenthesis) {
			println("\"");
			return new OptCpsExpr();
		}
		throw new GrammarException("OptCpsExpr");
	}

	private ICpsExpr cpsExpr() throws GrammarException {
		print("\"CpsExpr ::= ");
		if (terminal instanceof LeftParenthesis || terminal instanceof AddOperator
				|| terminal instanceof NotEqualsOperator || terminal instanceof Identifier
				|| terminal instanceof Literal) {
			println("<expr> <repCommaExpr>\"");
			IExpr expr = expr();
			IRepCommaExpr repCommaExpr = repCommaExpr();
			return new CpsExprExpr(expr, repCommaExpr);
		}
		throw new GrammarException("CpsExpr");
	}

	private IRepCommaExpr repCommaExpr() throws GrammarException {
		print("\"RepCommaExpr ::= ");
		if (terminal instanceof Comma) {
			println("COMMA <cpsExpr>\"");
			Comma comma = ((Comma) consume(Comma.class));
			ICpsExpr cpsExpr = cpsExpr();
			return new RepCommaExprComma(comma, cpsExpr);
		}
		if (terminal instanceof RightParenthesis) {
			println("\"");
			return new RepCommaExpr();
		}
		throw new GrammarException("RepCommaExpr");
	}

	private void print(String msg) {
		if (verbose) {
			System.out.print(msg);
		}
	}

	private void println(String msg) {
		if (verbose) {
			System.out.println(msg);
		}
	}
}
