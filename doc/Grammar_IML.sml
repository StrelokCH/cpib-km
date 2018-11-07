datatype term
  = ADDOPR
  | ATOMTYPE
  | BECOMES
  | BOOLOPR
  | CALL
  | CHANGEMODE
  | COLON
  | COMMA
  | DEBUGIN
  | DEBUGOUT
  | DO
  | ELSE
  | ENDFUN
  | ENDIF
  | ENDPROC
  | ENDPROGRAM
  | ENDWHILE
  | FLOWMODE
  | FUN
  | GLOBAL
  | IDENT
  | IF
  | INIT
  | LITERAL
  | LOCAL
  | LPAREN
  | MECHMODE
  | MULTOPR
  | NOT
  | PROC
  | PROGRAM
  | RELOPR
  | RETURNS
  | RPAREN
  | SEMICOLON
  | SKIP
  | THEN
  | WHILE


val string_of_term =
  fn ADDOPR         => "ADDOPR"
   | ATOMTYPE       => "ATOMTYPE"
   | BECOMES        => "BECOMES"
   | BOOLOPR        => "BOOLOPR"
   | CALL           => "CALL"
   | CHANGEMODE     => "CHANGEMODE"
   | COLON          => "COLON"
   | COMMA          => "COMMA"
   | DEBUGIN        => "DEBUGIN"
   | DEBUGOUT       => "DEBUGOUT"
   | DO             => "DO"
   | ELSE           => "ELSE"
   | ENDFUN         => "ENDFUN"
   | ENDIF          => "ENDIF"
   | ENDPROC        => "ENDPROC"
   | ENDPROGRAM     => "ENDPROGRAM"
   | ENDWHILE       => "ENDWHILE"
   | FLOWMODE       => "FLOWMODE"
   | FUN            => "FUN"
   | GLOBAL         => "GLOBAL"
   | IDENT          => "IDENT"
   | IF             => "IF"
   | INIT           => "INIT"
   | LITERAL        => "LITERAL"
   | LOCAL          => "LOCAL"
   | LPAREN         => "LPAREN"
   | MECHMODE       => "MECHMODE"
   | MULTOPR        => "MULTOPR"
   | NOT            => "NOT"
   | PROC           => "PROC"
   | PROGRAM        => "PROGRAM"
   | RELOPR         => "RELOPR"
   | RETURNS        => "RETURNS"
   | RPAREN         => "RPAREN"
   | SEMICOLON      => "SEMICOLON"
   | SKIP           => "SKIP"
   | THEN           => "THEN"
   | WHILE          => "WHILE"

datatype nonterm
  = cmd
  | cpsCmd
  | repSemicolonCmd
  | cpsDecl
  | cpsParam
  | repSemicolonCpsDecl
  | cpsStoDecl
  | repSemicolonCpsStoDecl
  | decl
  | expr
  | repBooloprExpr
  | exprList
  | optCpsExpr
  | cpsExpr
  | repCommaExpr
  | factor
  | funDecl
  | globalGlobImps
  | globImp
  | globImps
  | repCommaGlobImps
  | globInits
  | ident1
  | idents
  | repCommaIdent
  | optLocalCpsStoDecl
  | monadicOpr
  | optChangemode
  | optFlowmode
  | optGlobInits
  | optMechmode
  | optCpsProgParam
  | cpsProgParam
  | repCommaProgParam
  | param
  | paramList
  | optCpsParam
  | repCommaParam
  | procDecl
  | progParam
  | progParamList
  | program
  | optGlobalCpsDecl
  | stoDecl
  | term1
  | optReloprTerm2
  | term2
  | repAddoprTerm3
  | term3
  | repMultoprFactor
  | typedIdent

val string_of_nonterm =
  fn cmd              => "cmd"
   | cpsCmd           => "cpsCmd"
   | repSemicolonCmd          => "repSemicolonCmd"
   | cpsDecl          => "cpsDecl"
   | cpsParam         => "cpsParam"
   | repSemicolonCpsDecl         => "repSemicolonCpsDecl"
   | cpsStoDecl       => "cpsStoDecl"
   | repSemicolonCpsStoDecl      => "repSemicolonCpsStoDecl"
   | decl             => "decl"
   | expr             => "expr"
   | repBooloprExpr            => "repBooloprExpr"
   | exprList         => "exprList"
   | optCpsExpr       => "optCpsExpr"
   | cpsExpr        => "cpsExpr"
   | repCommaExpr       => "repCommaExpr"
   | factor           => "factor"
   | funDecl          => "funDecl"
   | globalGlobImps   => "globalGlobImps"
   | globImp          => "globImp"
   | globImps         => "globImps"
   | repCommaGlobImps        => "repCommaGlobImps"
   | globInits        => "globInits"
   | ident1           => "ident1"
   | idents           => "idents"
   | repCommaIdent          => "repCommaIdent"
   | optLocalCpsStoDecl  => "optLocalCpsStoDecl"
   | monadicOpr       => "monadicOpr"
   | optChangemode    => "optChangemode"
   | optFlowmode      => "optFlowmode"
   | optGlobInits     => "optGlobInits"
   | optMechmode      => "optMechmode"
   | optCpsProgParam    => "optCpsProgParam"
   | cpsProgParam   => "cpsProgParam"
   | repCommaProgParam  => "repCommaProgParam"
   | param            => "param"
   | paramList        => "paramList"
   | optCpsParam       => "optCpsParam"
   | repCommaParam      => "repCommaParam"
   | procDecl         => "procDecl"
   | progParam        => "progParam"
   | progParamList    => "progParamList"
   | program          => "program"
   | optGlobalCpsDecl         => "optGlobalCpsDecl"
   | stoDecl          => "stoDecl"
   | term1            => "term1"
   | optReloprTerm2           => "optReloprTerm2"
   | term2            => "term2"
   | repAddoprTerm3           => "repAddoprTerm3"
   | term3            => "term3"
   | repMultoprFactor           => "repMultoprFactor"
   | typedIdent       => "typedIdent"

val string_of_gramsym = (string_of_term, string_of_nonterm)

local
  open FixFoxi.FixFoxiCore
in

val productions =
[
( program, [[ T PROGRAM, T IDENT, N progParamList, N optGlobalCpsDecl, T DO, N cpsCmd, T ENDPROGRAM]]),
( optGlobalCpsDecl,[[ T GLOBAL, N cpsDecl],[]]),
( decl, [[ N stoDecl ],[ N funDecl ],[ N procDecl]]),
( stoDecl, [[ T CHANGEMODE, N typedIdent ],[ N typedIdent]]),
( funDecl, [[ T FUN, T IDENT, N paramList, T RETURNS, N stoDecl, N globalGlobImps, N optLocalCpsStoDecl, T DO, N cpsCmd, T ENDFUN]]),
( globalGlobImps, [[ T GLOBAL, N globImps],[]]),
( optLocalCpsStoDecl, [[ T LOCAL, N cpsStoDecl ],[]]),
( procDecl, [[ T PROC, T IDENT, N paramList, N globalGlobImps, N optLocalCpsStoDecl, T DO, N cpsCmd, T ENDPROC]]),
( globImps, [[ N globImp, N repCommaGlobImps ]]),
( repCommaGlobImps, [[ T COMMA, N globImp, N repCommaGlobImps ],[]]),
( globImp, [[ N optFlowmode, N optChangemode, T IDENT ]]),
( optFlowmode, [[ T FLOWMODE],[]]),
( optChangemode, [[ T CHANGEMODE],[]]),
( optMechmode, [[ T MECHMODE],[]]),
( cpsDecl, [[ N decl, N repSemicolonCpsDecl]]),
( repSemicolonCpsDecl, [[ T SEMICOLON, N cpsDecl ],[]]),
( cpsStoDecl, [[ N stoDecl, N repSemicolonCpsStoDecl]]),
( repSemicolonCpsStoDecl, [[ T SEMICOLON, N cpsStoDecl ],[]]),
( progParamList, [[ T LPAREN, N optCpsProgParam, T RPAREN]]),
( optCpsProgParam, [[ N cpsProgParam],[]]),
( cpsProgParam, [[ N progParam, N repCommaProgParam]]),
( repCommaProgParam, [[ T COMMA, N cpsProgParam],[]]),
( progParam, [[ N optFlowmode, N optChangemode, N typedIdent ]]),
( paramList, [[ T LPAREN, N optCpsParam, T RPAREN ]]),
( optCpsParam, [[ N cpsParam],[]]),
( cpsParam, [[N param, N repCommaParam]]),
( repCommaParam, [[ T COMMA, N cpsParam],[]]),
( param, [[ N optFlowmode, N optMechmode, N optChangemode, N typedIdent]]),
( typedIdent, [[ T IDENT, T COLON, T ATOMTYPE]]),
( cmd, [[ T SKIP],[ N expr, T BECOMES, N expr],[ T IF, N expr, T THEN, N cpsCmd, T ELSE, N cpsCmd, T ENDIF ],[ T WHILE, N expr, T DO, N cpsCmd, T ENDWHILE ],[ T CALL, T IDENT, N exprList, N optGlobInits ],[ T DEBUGIN, N expr ],[ T DEBUGOUT, N expr]]),
( optGlobInits, [[ N globInits ],[]]),
( cpsCmd, [[ N cmd, N repSemicolonCmd]]),
( repSemicolonCmd, [[ T SEMICOLON, N cpsCmd],[]]),
( globInits, [[ T INIT, N idents]]),
( idents, [[ T IDENT, N repCommaIdent]]),
( repCommaIdent, [[ T COMMA, N idents],[]]),
( expr, [[ N term1, N repBooloprExpr]]),
( repBooloprExpr, [[ T BOOLOPR, N expr],[]]),
( term1, [[ N term2, N optReloprTerm2]]),
( optReloprTerm2, [[ T RELOPR, N term2 ],[]]),
( term2, [[ N term3, N repAddoprTerm3]]),
( repAddoprTerm3, [[ T ADDOPR, N term2],[]]),
( term3, [[ N factor, N repMultoprFactor ]]),
( repMultoprFactor, [[ T MULTOPR, N term3],[]]),
( factor, [[ T LITERAL],[ T IDENT, N ident1 ],[ N monadicOpr, N factor],[ T LPAREN, N expr, T RPAREN]]),
( ident1, [[ T INIT],[ N exprList ],[]]),
( monadicOpr, [[ T NOT ],[ T ADDOPR]]),
( exprList, [[ T LPAREN, N optCpsExpr, T RPAREN]]),
( optCpsExpr, [[ N cpsExpr],[]]),
( cpsExpr, [[ N expr, N repCommaExpr]]),
( repCommaExpr, [[ T COMMA, N cpsExpr ],[]])
]

val S = program

val result = fix_foxi productions S string_of_gramsym

end (* local *)
