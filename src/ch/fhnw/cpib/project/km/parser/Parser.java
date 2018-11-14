package ch.fhnw.cpib.project.km.parser;

import ch.fhnw.cpib.project.km.token.BaseToken;
import ch.fhnw.cpib.project.km.token.ITokenList;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

import ch.fhnw.cpib.project.km.exceptions.*;
import ch.fhnw.cpib.project.km.syntax.concrete.*;

public class Parser {

	private ITokenList tokenList;
	private BaseToken terminal;
	// private IConcSyn concSyn; // discussed later

	public Parser(ITokenList tokenList) {
		this.tokenList = tokenList;
		this.tokenList.reset();
		// precondition: token list contains at least the SENTINEL
		terminal = tokenList.nextToken();
		// concSyn = new ConcSyn(); // discussed later
	}

	public IProgram parse() throws GrammarError {
		// parsing the start symbol ...
		IProgram program = program();
		// ... and then consuming the SENTINEL
		consume(Sentinel.class);
		return program;
	}

	private BaseToken consume(Class<?> expectedTerminal) throws GrammarError {
		if (expectedTerminal.isInstance(terminal)) {
			BaseToken consumedToken = terminal;
			if (!Sentinel.class.isInstance(terminal)) {
				terminal = tokenList.nextToken();
			}
			return consumedToken;
		} else {
			throw new GrammarError(
					"terminal expected: " + expectedTerminal.getName() + ", terminal found: " + terminal);
		}
	}

	private IProgram program() throws GrammarError {
		System.out.print("\"Program ::= ");
		if (terminal instanceof Program) {
			System.out.println("PROGRAM IDENT <progParamList> <optGlobalCpsDecl> DO <cpsCmd> ENDPROGRAM\"");
			Program program = ((Program) consume(Program.class));
			Identifier ident = ((Identifier) consume(Identifier.class));
			IProgParamList progParamList = progParamList();
			IOptGlobalCpsDecl optGlobalCpsDecl = optGlobalCpsDecl();
			Do aDo = ((Do) consume(Do.class));
			ICpsCmd cpsCmd = cpsCmd();
			EndProgram endprogram = ((EndProgram) consume(EndProgram.class));
			return new ProgramProgram(program, ident, progParamList, optGlobalCpsDecl, aDo, cpsCmd, endprogram);
		}
		throw new GrammarError("Program");
	}

	private IOptGlobalCpsDecl optGlobalCpsDecl() throws GrammarError {
		System.out.print("\"OptGlobalCpsDecl ::= ");
		if (terminal instanceof Global) {
			System.out.println("GLOBAL <cpsDecl>\"");
			Global global = ((Global) consume(Global.class));
			ICpsDecl cpsDecl = cpsDecl();
			return new OptGlobalCpsDeclGlobal(global, cpsDecl);
		}
		if (terminal instanceof Do) {
			System.out.println("\"");
			return new OptGlobalCpsDecl();
		}
		throw new GrammarError("OptGlobalCpsDecl");
	}

	private IDecl decl() throws GrammarError {
		System.out.print("\"Decl ::= ");
		if (terminal instanceof Identifier || terminal instanceof ChangeMode) {
			System.out.println("<stoDecl>\"");
			IStoDecl stoDecl = stoDecl();
			return new DeclStoDecl(stoDecl);
		}
		if (terminal instanceof Function) {
			System.out.println("<funDecl>\"");
			IFunDecl funDecl = funDecl();
			return new DeclFunDecl(funDecl);
		}
		if (terminal instanceof Procedure) {
			System.out.println("<procDecl>\"");
			IProcDecl procDecl = procDecl();
			return new DeclProcDecl(procDecl);
		}
		throw new GrammarError("Decl");
	}

	private IStoDecl stoDecl() throws GrammarError {
		System.out.print("\"StoDecl ::= ");
		if (terminal instanceof ChangeMode) {
			System.out.println("CHANGEMODE <typedIdent>\"");
			ChangeMode changemode = ((ChangeMode) consume(ChangeMode.class));
			ITypedIdent typedIdent = typedIdent();
			return new StoDeclChangemode(changemode, typedIdent);
		}
		if (terminal instanceof Identifier) {
			System.out.println("<typedIdent>\"");
			ITypedIdent typedIdent = typedIdent();
			return new StoDeclTypedIdent(typedIdent);
		}
		throw new GrammarError("StoDecl");
	}

	private IFunDecl funDecl() throws GrammarError {
		System.out.print("\"FunDecl ::= ");
		if (terminal instanceof Function) {
			System.out.println(
					"FUN IDENT <paramList> RETURNS <stoDecl> <globalGlobImps> <optLocalCpsStoDecl> DO <cpsCmd> ENDFUN\"");
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
		throw new GrammarError("FunDecl");
	}

	private IGlobalGlobImps globalGlobImps() throws GrammarError {
		System.out.print("\"GlobalGlobImps ::= ");
		if (terminal instanceof Global) {
			System.out.println("GLOBAL <globImps>\"");
			Global global = ((Global) consume(Global.class));
			IGlobImps globImps = globImps();
			return new GlobalGlobImpsGlobal(global, globImps);
		}
		if (terminal instanceof Do || terminal instanceof Local) {
			System.out.println("\"");
			return new GlobalGlobImps();
		}
		throw new GrammarError("GlobalGlobImps");
	}

	private IOptLocalCpsStoDecl optLocalCpsStoDecl() throws GrammarError {
		System.out.print("\"OptLocalCpsStoDecl ::= ");
		if (terminal instanceof Local) {
			System.out.println("LOCAL <cpsStoDecl>\"");
			Local local = ((Local) consume(Local.class));
			ICpsStoDecl cpsStoDecl = cpsStoDecl();
			return new OptLocalCpsStoDeclLocal(local, cpsStoDecl);
		}
		if (terminal instanceof Do) {
			System.out.println("\"");
			return new OptLocalCpsStoDecl();
		}
		throw new GrammarError("OptLocalCpsStoDecl");
	}

	private IProcDecl procDecl() throws GrammarError {
		System.out.print("\"ProcDecl ::= ");
		if (terminal instanceof Procedure) {
			System.out.println("PROC IDENT <paramList> <globalGlobImps> <optLocalCpsStoDecl> DO <cpsCmd> ENDPROC\"");
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
		throw new GrammarError("ProcDecl");
	}

	private IGlobImps globImps() throws GrammarError {
		System.out.print("\"GlobImps ::= ");
		if (terminal instanceof Identifier || terminal instanceof ChangeMode || terminal instanceof Flowmode) {
			System.out.println("<globImp> <repCommaGlobImps>\"");
			IGlobImp globImp = globImp();
			IRepCommaGlobImps repCommaGlobImps = repCommaGlobImps();
			return new GlobImpsGlobImp(globImp, repCommaGlobImps);
		}
		throw new GrammarError("GlobImps");
	}

	private IRepCommaGlobImps repCommaGlobImps() throws GrammarError {
		System.out.print("\"RepCommaGlobImps ::= ");
		if (terminal instanceof Comma) {
			System.out.println("COMMA <globImp> <repCommaGlobImps>\"");
			Comma comma = ((Comma) consume(Comma.class));
			IGlobImp globImp = globImp();
			IRepCommaGlobImps repCommaGlobImps = repCommaGlobImps();
			return new RepCommaGlobImpsComma(comma, globImp, repCommaGlobImps);
		}
		if (terminal instanceof Do || terminal instanceof Local) {
			System.out.println("\"");
			return new RepCommaGlobImps();
		}
		throw new GrammarError("RepCommaGlobImps");
	}

	private IGlobImp globImp() throws GrammarError {
		System.out.print("\"GlobImp ::= ");
		if (terminal instanceof Identifier || terminal instanceof ChangeMode || terminal instanceof Flowmode) {
			System.out.println("<optFlowmode> <optChangemode> IDENT\"");
			IOptFlowmode optFlowmode = optFlowmode();
			IOptChangemode optChangemode = optChangemode();
			Identifier ident = ((Identifier) consume(Identifier.class));
			return new GlobImpOptFlowmode(optFlowmode, optChangemode, ident);
		}
		throw new GrammarError("GlobImp");
	}

	private IOptFlowmode optFlowmode() throws GrammarError {
		System.out.print("\"OptFlowmode ::= ");
		if (terminal instanceof Flowmode) {
			System.out.println("FLOWMODE\"");
			Flowmode flowmode = ((Flowmode) consume(Flowmode.class));
			return new OptFlowmodeFlowmode(flowmode);
		}
		if (terminal instanceof Mechmode || terminal instanceof Identifier || terminal instanceof ChangeMode) {
			System.out.println("\"");
			return new OptFlowmode();
		}
		throw new GrammarError("OptFlowmode");
	}

	private IOptChangemode optChangemode() throws GrammarError {
		System.out.print("\"OptChangemode ::= ");
		if (terminal instanceof ChangeMode) {
			System.out.println("CHANGEMODE\"");
			ChangeMode changemode = ((ChangeMode) consume(ChangeMode.class));
			return new OptChangemodeChangemode(changemode);
		}
		if (terminal instanceof Identifier) {
			System.out.println("\"");
			return new OptChangemode();
		}
		throw new GrammarError("OptChangemode");
	}

	private IOptMechmode optMechmode() throws GrammarError {
		System.out.print("\"OptMechmode ::= ");
		if (terminal instanceof Mechmode) {
			System.out.println("MECHMODE\"");
			Mechmode mechmode = ((Mechmode) consume(Mechmode.class));
			return new OptMechmodeMechmode(mechmode);
		}
		if (terminal instanceof Identifier || terminal instanceof ChangeMode) {
			System.out.println("\"");
			return new OptMechmode();
		}
		throw new GrammarError("OptMechmode");
	}

	private ICpsDecl cpsDecl() throws GrammarError {
		System.out.print("\"CpsDecl ::= ");
		if (terminal instanceof Procedure || terminal instanceof Function || terminal instanceof Identifier
				|| terminal instanceof ChangeMode) {
			System.out.println("<decl> <repSemicolonCpsDecl>\"");
			IDecl decl = decl();
			IRepSemicolonCpsDecl repSemicolonCpsDecl = repSemicolonCpsDecl();
			return new CpsDeclDecl(decl, repSemicolonCpsDecl);
		}
		throw new GrammarError("CpsDecl");
	}

	private IRepSemicolonCpsDecl repSemicolonCpsDecl() throws GrammarError {
		System.out.print("\"RepSemicolonCpsDecl ::= ");
		if (terminal instanceof Semicolon) {
			System.out.println("SEMICOLON <cpsDecl>\"");
			Semicolon semicolon = ((Semicolon) consume(Semicolon.class));
			ICpsDecl cpsDecl = cpsDecl();
			return new RepSemicolonCpsDeclSemicolon(semicolon, cpsDecl);
		}
		if (terminal instanceof Do) {
			System.out.println("\"");
			return new RepSemicolonCpsDecl();
		}
		throw new GrammarError("RepSemicolonCpsDecl");
	}

	private ICpsStoDecl cpsStoDecl() throws GrammarError {
		System.out.print("\"CpsStoDecl ::= ");
		if (terminal instanceof Identifier || terminal instanceof ChangeMode) {
			System.out.println("<stoDecl> <repSemicolonCpsStoDecl>\"");
			IStoDecl stoDecl = stoDecl();
			IRepSemicolonCpsStoDecl repSemicolonCpsStoDecl = repSemicolonCpsStoDecl();
			return new CpsStoDeclStoDecl(stoDecl, repSemicolonCpsStoDecl);
		}
		throw new GrammarError("CpsStoDecl");
	}

	private IRepSemicolonCpsStoDecl repSemicolonCpsStoDecl() throws GrammarError {
		System.out.print("\"RepSemicolonCpsStoDecl ::= ");
		if (terminal instanceof Semicolon) {
			System.out.println("SEMICOLON <cpsStoDecl>\"");
			Semicolon semicolon = ((Semicolon) consume(Semicolon.class));
			ICpsStoDecl cpsStoDecl = cpsStoDecl();
			return new RepSemicolonCpsStoDeclSemicolon(semicolon, cpsStoDecl);
		}
		if (terminal instanceof Do) {
			System.out.println("\"");
			return new RepSemicolonCpsStoDecl();
		}
		throw new GrammarError("RepSemicolonCpsStoDecl");
	}

	private IProgParamList progParamList() throws GrammarError {
		System.out.print("\"ProgParamList ::= ");
		if (terminal instanceof LeftParenthesis) {
			System.out.println("LPAREN <optCpsProgParam> RPAREN\"");
			LeftParenthesis lparen = ((LeftParenthesis) consume(LeftParenthesis.class));
			IOptCpsProgParam optCpsProgParam = optCpsProgParam();
			RightParenthesis rparen = ((RightParenthesis) consume(RightParenthesis.class));
			return new ProgParamListLparen(lparen, optCpsProgParam, rparen);
		}
		throw new GrammarError("ProgParamList");
	}

	private IOptCpsProgParam optCpsProgParam() throws GrammarError {
		System.out.print("\"OptCpsProgParam ::= ");
		if (terminal instanceof Identifier || terminal instanceof ChangeMode || terminal instanceof Flowmode) {
			System.out.println("<cpsProgParam>\"");
			ICpsProgParam cpsProgParam = cpsProgParam();
			return new OptCpsProgParamCpsProgParam(cpsProgParam);
		}
		if (terminal instanceof RightParenthesis) {
			System.out.println("\"");
			return new OptCpsProgParam();
		}
		throw new GrammarError("OptCpsProgParam");
	}

	private ICpsProgParam cpsProgParam() throws GrammarError {
		System.out.print("\"CpsProgParam ::= ");
		if (terminal instanceof Identifier || terminal instanceof ChangeMode || terminal instanceof Flowmode) {
			System.out.println("<progParam> <repCommaProgParam>\"");
			IProgParam progParam = progParam();
			IRepCommaProgParam repCommaProgParam = repCommaProgParam();
			return new CpsProgParamProgParam(progParam, repCommaProgParam);
		}
		throw new GrammarError("CpsProgParam");
	}

	private IRepCommaProgParam repCommaProgParam() throws GrammarError {
		System.out.print("\"RepCommaProgParam ::= ");
		if (terminal instanceof Comma) {
			System.out.println("COMMA <cpsProgParam>\"");
			Comma comma = ((Comma) consume(Comma.class));
			ICpsProgParam cpsProgParam = cpsProgParam();
			return new RepCommaProgParamComma(comma, cpsProgParam);
		}
		if (terminal instanceof RightParenthesis) {
			System.out.println("\"");
			return new RepCommaProgParam();
		}
		throw new GrammarError("RepCommaProgParam");
	}

	private IProgParam progParam() throws GrammarError {
		System.out.print("\"ProgParam ::= ");
		if (terminal instanceof Identifier || terminal instanceof ChangeMode || terminal instanceof Flowmode) {
			System.out.println("<optFlowmode> <optChangemode> <typedIdent>\"");
			IOptFlowmode optFlowmode = optFlowmode();
			IOptChangemode optChangemode = optChangemode();
			ITypedIdent typedIdent = typedIdent();
			return new ProgParamOptFlowmode(optFlowmode, optChangemode, typedIdent);
		}
		throw new GrammarError("ProgParam");
	}

	private IParamList paramList() throws GrammarError {
		System.out.print("\"ParamList ::= ");
		if (terminal instanceof LeftParenthesis) {
			System.out.println("LPAREN <optCpsParam> RPAREN\"");
			LeftParenthesis lparen = ((LeftParenthesis) consume(LeftParenthesis.class));
			IOptCpsParam optCpsParam = optCpsParam();
			RightParenthesis rparen = ((RightParenthesis) consume(RightParenthesis.class));
			return new ParamListLparen(lparen, optCpsParam, rparen);
		}
		throw new GrammarError("ParamList");
	}

	private IOptCpsParam optCpsParam() throws GrammarError {
		System.out.print("\"OptCpsParam ::= ");
		if (terminal instanceof Identifier || terminal instanceof ChangeMode || terminal instanceof Mechmode
				|| terminal instanceof Flowmode) {
			System.out.println("<cpsParam>\"");
			ICpsParam cpsParam = cpsParam();
			return new OptCpsParamCpsParam(cpsParam);
		}
		if (terminal instanceof RightParenthesis) {
			System.out.println("\"");
			return new OptCpsParam();
		}
		throw new GrammarError("OptCpsParam");
	}

	private ICpsParam cpsParam() throws GrammarError {
		System.out.print("\"CpsParam ::= ");
		if (terminal instanceof Identifier || terminal instanceof ChangeMode || terminal instanceof Mechmode
				|| terminal instanceof Flowmode) {
			System.out.println("<param> <repCommaParam>\"");
			IParam param = param();
			IRepCommaParam repCommaParam = repCommaParam();
			return new CpsParamParam(param, repCommaParam);
		}
		throw new GrammarError("CpsParam");
	}

	private IRepCommaParam repCommaParam() throws GrammarError {
		System.out.print("\"RepCommaParam ::= ");
		if (terminal instanceof Comma) {
			System.out.println("COMMA <cpsParam>\"");
			Comma comma = ((Comma) consume(Comma.class));
			ICpsParam cpsParam = cpsParam();
			return new RepCommaParamComma(comma, cpsParam);
		}
		if (terminal instanceof RightParenthesis) {
			System.out.println("\"");
			return new RepCommaParam();
		}
		throw new GrammarError("RepCommaParam");
	}

	private IParam param() throws GrammarError {
		System.out.print("\"Param ::= ");
		if (terminal instanceof Identifier || terminal instanceof ChangeMode || terminal instanceof Mechmode
				|| terminal instanceof Flowmode) {
			System.out.println("<optFlowmode> <optMechmode> <optChangemode> <typedIdent>\"");
			IOptFlowmode optFlowmode = optFlowmode();
			IOptMechmode optMechmode = optMechmode();
			IOptChangemode optChangemode = optChangemode();
			ITypedIdent typedIdent = typedIdent();
			return new ParamOptFlowmode(optFlowmode, optMechmode, optChangemode, typedIdent);
		}
		throw new GrammarError("Param");
	}

	private ITypedIdent typedIdent() throws GrammarError {
		System.out.print("\"TypedIdent ::= ");
		if (terminal instanceof Identifier) {
			System.out.println("IDENT COLON ATOMTYPE\"");
			Identifier ident = ((Identifier) consume(Identifier.class));
			Colon colon = ((Colon) consume(Colon.class));
			Type atomtype = ((Type) consume(Type.class));
			return new TypedIdentIdent(ident, colon, atomtype);
		}
		throw new GrammarError("TypedIdent");
	}

	private ICmd cmd() throws GrammarError {
		System.out.print("\"Cmd ::= ");
		if (terminal instanceof Skip) {
			System.out.println("SKIP\"");
			Skip skip = ((Skip) consume(Skip.class));
			return new CmdSkip(skip);
		}
		if (terminal instanceof LeftParenthesis || terminal instanceof AddOperator || terminal instanceof NotEqualsOperator
				|| terminal instanceof Identifier || terminal instanceof Literal) {
			System.out.println("<expr> BECOMES <expr>\"");
			IExpr expr = expr();
			Becomes becomes = ((Becomes) consume(Becomes.class));
			IExpr expr2 = expr();
			return new CmdExpr(expr, becomes, expr2);
		}
		if (terminal instanceof If) {
			System.out.println("IF <expr> THEN <cpsCmd> ELSE <cpsCmd> ENDIF\"");
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
			System.out.println("WHILE <expr> DO <cpsCmd> ENDWHILE\"");
			While aWhile = ((While) consume(While.class));
			IExpr expr = expr();
			Do aDo = ((Do) consume(Do.class));
			ICpsCmd cpsCmd = cpsCmd();
			EndWhile endwhile = ((EndWhile) consume(EndWhile.class));
			return new CmdWhile(aWhile, expr, aDo, cpsCmd, endwhile);
		}
		if (terminal instanceof Call) {
			System.out.println("CALL IDENT <exprList> <optGlobInits>\"");
			Call call = ((Call) consume(Call.class));
			Identifier ident = ((Identifier) consume(Identifier.class));
			IExprList exprList = exprList();
			IOptGlobInits optGlobInits = optGlobInits();
			return new CmdCall(call, ident, exprList, optGlobInits);
		}
		if (terminal instanceof DebugIn) {
			System.out.println("DEBUGIN <expr>\"");
			DebugIn debugin = ((DebugIn) consume(DebugIn.class));
			IExpr expr = expr();
			return new CmdDebugin(debugin, expr);
		}
		if (terminal instanceof DebugOut) {
			System.out.println("DEBUGOUT <expr>\"");
			DebugOut debugout = ((DebugOut) consume(DebugOut.class));
			IExpr expr = expr();
			return new CmdDebugout(debugout, expr);
		}
		throw new GrammarError("Cmd");
	}

	private IOptGlobInits optGlobInits() throws GrammarError {
		System.out.print("\"OptGlobInits ::= ");
		if (terminal instanceof Init) {
			System.out.println("<globInits>\"");
			IGlobInits globInits = globInits();
			return new OptGlobInitsGlobInits(globInits);
		}
		if (terminal instanceof EndWhile || terminal instanceof EndIf || terminal instanceof Else
				|| terminal instanceof EndProcedure || terminal instanceof EndFunction || terminal instanceof EndProgram
				|| terminal instanceof Semicolon) {
			System.out.println("\"");
			return new OptGlobInits();
		}
		throw new GrammarError("OptGlobInits");
	}

	private ICpsCmd cpsCmd() throws GrammarError {
		System.out.print("\"CpsCmd ::= ");
		if (terminal instanceof DebugOut || terminal instanceof DebugIn || terminal instanceof Call
				|| terminal instanceof While || terminal instanceof If || terminal instanceof LeftParenthesis
				|| terminal instanceof AddOperator || terminal instanceof NotEqualsOperator || terminal instanceof Identifier
				|| terminal instanceof Literal || terminal instanceof Skip) {
			System.out.println("<cmd> <repSemicolonCmd>\"");
			ICmd cmd = cmd();
			IRepSemicolonCmd repSemicolonCmd = repSemicolonCmd();
			return new CpsCmdCmd(cmd, repSemicolonCmd);
		}
		throw new GrammarError("CpsCmd");
	}

	private IRepSemicolonCmd repSemicolonCmd() throws GrammarError {
		System.out.print("\"RepSemicolonCmd ::= ");
		if (terminal instanceof Semicolon) {
			System.out.println("SEMICOLON <cpsCmd>\"");
			Semicolon semicolon = ((Semicolon) consume(Semicolon.class));
			ICpsCmd cpsCmd = cpsCmd();
			return new RepSemicolonCmdSemicolon(semicolon, cpsCmd);
		}
		if (terminal instanceof EndWhile || terminal instanceof EndIf || terminal instanceof Else
				|| terminal instanceof EndProcedure || terminal instanceof EndFunction || terminal instanceof EndProgram) {
			System.out.println("\"");
			return new RepSemicolonCmd();
		}
		throw new GrammarError("RepSemicolonCmd");
	}

	private IGlobInits globInits() throws GrammarError {
		System.out.print("\"GlobInits ::= ");
		if (terminal instanceof Init) {
			System.out.println("INIT <idents>\"");
			Init init = ((Init) consume(Init.class));
			IIdents idents = idents();
			return new GlobInitsInit(init, idents);
		}
		throw new GrammarError("GlobInits");
	}

	private IIdents idents() throws GrammarError {
		System.out.print("\"Idents ::= ");
		if (terminal instanceof Identifier) {
			System.out.println("IDENT <repCommaIdent>\"");
			Identifier ident = ((Identifier) consume(Identifier.class));
			IRepCommaIdent repCommaIdent = repCommaIdent();
			return new IdentsIdent(ident, repCommaIdent);
		}
		throw new GrammarError("Idents");
	}

	private IRepCommaIdent repCommaIdent() throws GrammarError {
		System.out.print("\"RepCommaIdent ::= ");
		if (terminal instanceof Comma) {
			System.out.println("COMMA <idents>\"");
			Comma comma = ((Comma) consume(Comma.class));
			IIdents idents = idents();
			return new RepCommaIdentComma(comma, idents);
		}
		if (terminal instanceof EndWhile || terminal instanceof EndIf || terminal instanceof Else
				|| terminal instanceof EndProcedure || terminal instanceof EndFunction || terminal instanceof EndProgram
				|| terminal instanceof Semicolon) {
			System.out.println("\"");
			return new RepCommaIdent();
		}
		throw new GrammarError("RepCommaIdent");
	}

	private IExpr expr() throws GrammarError {
		System.out.print("\"Expr ::= ");
		if (terminal instanceof LeftParenthesis || terminal instanceof AddOperator || terminal instanceof NotEqualsOperator
				|| terminal instanceof Identifier || terminal instanceof Literal) {
			System.out.println("<term1> <repBooloprExpr>\"");
			ITerm1 term1 = term1();
			IRepBooloprExpr repBooloprExpr = repBooloprExpr();
			return new ExprTerm1(term1, repBooloprExpr);
		}
		throw new GrammarError("Expr");
	}

	private IRepBooloprExpr repBooloprExpr() throws GrammarError {
		System.out.print("\"RepBooloprExpr ::= ");
		if (terminal instanceof BooleanOperator) {
			System.out.println("BOOLOPR <expr>\"");
			BooleanOperator boolopr = ((BooleanOperator) consume(BooleanOperator.class));
			IExpr expr = expr();
			return new RepBooloprExprBoolopr(boolopr, expr);
		}
		if (terminal instanceof Comma || terminal instanceof RightParenthesis || terminal instanceof Do
				|| terminal instanceof Then || terminal instanceof EndWhile || terminal instanceof EndIf
				|| terminal instanceof Else || terminal instanceof EndProcedure || terminal instanceof EndFunction
				|| terminal instanceof EndProgram || terminal instanceof Semicolon || terminal instanceof Becomes) {
			System.out.println("\"");
			return new RepBooloprExpr();
		}
		throw new GrammarError("RepBooloprExpr");
	}

	private ITerm1 term1() throws GrammarError {
		System.out.print("\"Term1 ::= ");
		if (terminal instanceof LeftParenthesis || terminal instanceof AddOperator || terminal instanceof NotEqualsOperator
				|| terminal instanceof Identifier || terminal instanceof Literal) {
			System.out.println("<term2> <optReloprTerm2>\"");
			ITerm2 term2 = term2();
			IOptReloprTerm2 optReloprTerm2 = optReloprTerm2();
			return new Term1Term2(term2, optReloprTerm2);
		}
		throw new GrammarError("Term1");
	}

	private IOptReloprTerm2 optReloprTerm2() throws GrammarError {
		System.out.print("\"OptReloprTerm2 ::= ");
		if (terminal instanceof RelationalOperator) {
			System.out.println("RELOPR <term2>\"");
			RelationalOperator relopr = ((RelationalOperator) consume(RelationalOperator.class));
			ITerm2 term2 = term2();
			return new OptReloprTerm2Relopr(relopr, term2);
		}
		if (terminal instanceof Comma || terminal instanceof RightParenthesis || terminal instanceof Do
				|| terminal instanceof Then || terminal instanceof EndWhile || terminal instanceof EndIf
				|| terminal instanceof Else || terminal instanceof EndProcedure || terminal instanceof EndFunction
				|| terminal instanceof EndProgram || terminal instanceof Semicolon || terminal instanceof Becomes
				|| terminal instanceof BooleanOperator) {
			System.out.println("\"");
			return new OptReloprTerm2();
		}
		throw new GrammarError("OptReloprTerm2");
	}

	private ITerm2 term2() throws GrammarError {
		System.out.print("\"Term2 ::= ");
		if (terminal instanceof LeftParenthesis || terminal instanceof AddOperator || terminal instanceof NotEqualsOperator
				|| terminal instanceof Identifier || terminal instanceof Literal) {
			System.out.println("<term3> <repAddoprTerm3>\"");
			ITerm3 term3 = term3();
			IRepAddoprTerm3 repAddoprTerm3 = repAddoprTerm3();
			return new Term2Term3(term3, repAddoprTerm3);
		}
		throw new GrammarError("Term2");
	}

	private IRepAddoprTerm3 repAddoprTerm3() throws GrammarError {
		System.out.print("\"RepAddoprTerm3 ::= ");
		if (terminal instanceof AddOperator) {
			System.out.println("ADDOPR <term2>\"");
			AddOperator addopr = ((AddOperator) consume(AddOperator.class));
			ITerm2 term2 = term2();
			return new RepAddoprTerm3Addopr(addopr, term2);
		}
		if (terminal instanceof Comma || terminal instanceof RightParenthesis || terminal instanceof Do
				|| terminal instanceof Then || terminal instanceof EndWhile || terminal instanceof EndIf
				|| terminal instanceof Else || terminal instanceof EndProcedure || terminal instanceof EndFunction
				|| terminal instanceof EndProgram || terminal instanceof Semicolon || terminal instanceof Becomes
				|| terminal instanceof BooleanOperator || terminal instanceof RelationalOperator) {
			System.out.println("\"");
			return new RepAddoprTerm3();
		}
		throw new GrammarError("RepAddoprTerm3");
	}

	private ITerm3 term3() throws GrammarError {
		System.out.print("\"Term3 ::= ");
		if (terminal instanceof LeftParenthesis || terminal instanceof AddOperator || terminal instanceof NotEqualsOperator
				|| terminal instanceof Identifier || terminal instanceof Literal) {
			System.out.println("<factor> <repMultoprFactor>\"");
			IFactor factor = factor();
			IRepMultoprFactor repMultoprFactor = repMultoprFactor();
			return new Term3Factor(factor, repMultoprFactor);
		}
		throw new GrammarError("Term3");
	}

	private IRepMultoprFactor repMultoprFactor() throws GrammarError {
		System.out.print("\"RepMultoprFactor ::= ");
		if (terminal instanceof MultiplicationOperator) {
			System.out.println("MULTOPR <term3>\"");
			MultiplicationOperator multopr = ((MultiplicationOperator) consume(MultiplicationOperator.class));
			ITerm3 term3 = term3();
			return new RepMultoprFactorMultopr(multopr, term3);
		}
		if (terminal instanceof Comma || terminal instanceof RightParenthesis || terminal instanceof Do
				|| terminal instanceof Then || terminal instanceof EndWhile || terminal instanceof EndIf
				|| terminal instanceof Else || terminal instanceof EndProcedure || terminal instanceof EndFunction
				|| terminal instanceof EndProgram || terminal instanceof Semicolon || terminal instanceof Becomes
				|| terminal instanceof BooleanOperator || terminal instanceof RelationalOperator || terminal instanceof AddOperator) {
			System.out.println("\"");
			return new RepMultoprFactor();
		}
		throw new GrammarError("RepMultoprFactor");
	}

	private IFactor factor() throws GrammarError {
		System.out.print("\"Factor ::= ");
		if (terminal instanceof Literal) {
			System.out.println("LITERAL\"");
			Literal literal = ((Literal) consume(Literal.class));
			return new FactorLiteral(literal);
		}
		if (terminal instanceof Identifier) {
			System.out.println("IDENT <ident1>\"");
			Identifier ident = ((Identifier) consume(Identifier.class));
			IIdent1 ident1 = ident1();
			return new FactorIdent(ident, ident1);
		}
		if (terminal instanceof AddOperator || terminal instanceof NotEqualsOperator) {
			System.out.println("<monadicOpr> <factor>\"");
			IMonadicOpr monadicOpr = monadicOpr();
			IFactor factor = factor();
			return new FactorMonadicOpr(monadicOpr, factor);
		}
		if (terminal instanceof LeftParenthesis) {
			System.out.println("LPAREN <expr> RPAREN\"");
			LeftParenthesis lparen = ((LeftParenthesis) consume(LeftParenthesis.class));
			IExpr expr = expr();
			RightParenthesis rparen = ((RightParenthesis) consume(RightParenthesis.class));
			return new FactorLparen(lparen, expr, rparen);
		}
		throw new GrammarError("Factor");
	}

	private IIdent1 ident1() throws GrammarError {
		System.out.print("\"Ident1 ::= ");
		if (terminal instanceof Init) {
			System.out.println("INIT\"");
			Init init = ((Init) consume(Init.class));
			return new Ident1Init(init);
		}
		if (terminal instanceof LeftParenthesis) {
			System.out.println("<exprList>\"");
			IExprList exprList = exprList();
			return new Ident1ExprList(exprList);
		}
		if (terminal instanceof Comma || terminal instanceof RightParenthesis || terminal instanceof Do
				|| terminal instanceof Then || terminal instanceof EndWhile || terminal instanceof EndIf
				|| terminal instanceof Else || terminal instanceof EndProcedure || terminal instanceof EndFunction
				|| terminal instanceof EndProgram || terminal instanceof Semicolon || terminal instanceof Becomes
				|| terminal instanceof BooleanOperator || terminal instanceof RelationalOperator || terminal instanceof AddOperator
				|| terminal instanceof MultiplicationOperator) {
			System.out.println("\"");
			return new Ident1();
		}
		throw new GrammarError("Ident1");
	}

	private IMonadicOpr monadicOpr() throws GrammarError {
		System.out.print("\"MonadicOpr ::= ");
		if (terminal instanceof NotEqualsOperator) {
			System.out.println("NOT\"");
			NotOperator not = ((NotOperator) consume(NotOperator.class));
			return new MonadicOprNot(not);
		}
		if (terminal instanceof AddOperator) {
			System.out.println("ADDOPR\"");
			AddOperator addopr = ((AddOperator) consume(AddOperator.class));
			return new MonadicOprAddopr(addopr);
		}
		throw new GrammarError("MonadicOpr");
	}

	private IExprList exprList() throws GrammarError {
		System.out.print("\"ExprList ::= ");
		if (terminal instanceof LeftParenthesis) {
			System.out.println("LPAREN <optCpsExpr> RPAREN\"");
			LeftParenthesis lparen = ((LeftParenthesis) consume(LeftParenthesis.class));
			IOptCpsExpr optCpsExpr = optCpsExpr();
			RightParenthesis rparen = ((RightParenthesis) consume(RightParenthesis.class));
			return new ExprListLparen(lparen, optCpsExpr, rparen);
		}
		throw new GrammarError("ExprList");
	}

	private IOptCpsExpr optCpsExpr() throws GrammarError {
		System.out.print("\"OptCpsExpr ::= ");
		if (terminal instanceof LeftParenthesis || terminal instanceof AddOperator || terminal instanceof NotEqualsOperator
				|| terminal instanceof Identifier || terminal instanceof Literal) {
			System.out.println("<cpsExpr>\"");
			ICpsExpr cpsExpr = cpsExpr();
			return new OptCpsExprCpsExpr(cpsExpr);
		}
		if (terminal instanceof RightParenthesis) {
			System.out.println("\"");
			return new OptCpsExpr();
		}
		throw new GrammarError("OptCpsExpr");
	}

	private ICpsExpr cpsExpr() throws GrammarError {
		System.out.print("\"CpsExpr ::= ");
		if (terminal instanceof LeftParenthesis || terminal instanceof AddOperator || terminal instanceof NotEqualsOperator
				|| terminal instanceof Identifier || terminal instanceof Literal) {
			System.out.println("<expr> <repCommaExpr>\"");
			IExpr expr = expr();
			IRepCommaExpr repCommaExpr = repCommaExpr();
			return new CpsExprExpr(expr, repCommaExpr);
		}
		throw new GrammarError("CpsExpr");
	}

	private IRepCommaExpr repCommaExpr() throws GrammarError {
		System.out.print("\"RepCommaExpr ::= ");
		if (terminal instanceof Comma) {
			System.out.println("COMMA <cpsExpr>\"");
			Comma comma = ((Comma) consume(Comma.class));
			ICpsExpr cpsExpr = cpsExpr();
			return new RepCommaExprComma(comma, cpsExpr);
		}
		if (terminal instanceof RightParenthesis) {
			System.out.println("\"");
			return new RepCommaExpr();
		}
		throw new GrammarError("RepCommaExpr");
	}

}
