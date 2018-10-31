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
  | cpsCmd1
  | cpsDecl
  | cpsDecl1
  | cpsStoDecl
  | cpsStoDecl1
  | decl
  | expr
  | expr1
  | exprList
  | exprList1
  | exprList2
  | factor
  | funDecl
  | globalGlobImps
  | globImp
  | globImps
  | globImps1
  | globInits
  | ident1
  | idents
  | idents1
  | localCpsStoDecl
  | monadicOpr
  | optChangemode
  | optFlowmode
  | optGlobInits
  | optMechmode
  | optProgParams
  | optProgParams1
  | optProgParams2
  | param
  | paramList
  | paramList1
  | paramList2
  | procDecl
  | progParam
  | progParamList
  | program
  | program1
  | stoDecl
  | term1
  | term11
  | term2
  | term21
  | term3
  | term31
  | typedIdent

val string_of_nonterm =
  fn cmd              => "cmd"
   | cpsCmd           => "cpsCmd"
   | cpsCmd1          => "cpsCmd1"
   | cpsDecl          => "cpsDecl"
   | cpsDecl1         => "cpsDecl1"
   | cpsStoDecl       => "cpsStoDecl"
   | cpsStoDecl1      => "cpsStoDecl1"
   | decl             => "decl"
   | expr             => "expr"
   | expr1            => "expr1"
   | exprList         => "exprList"
   | exprList1        => "exprList1"
   | exprList2       => "exprList2"
   | factor           => "factor"
   | funDecl          => "funDecl"
   | globalGlobImps   => "globalGlobImps"
   | globImp          => "globImp"
   | globImps         => "globImps"
   | globImps1        => "globImps1"
   | globInits        => "globInits"
   | ident1           => "ident1"
   | idents           => "idents"
   | idents1          => "idents1"
   | localCpsStoDecl  => "localCpsStoDecl"
   | monadicOpr       => "monadicOpr"
   | optChangemode    => "optChangemode"
   | optFlowmode      => "optFlowmode"
   | optGlobInits     => "optGlobInits"
   | optMechmode      => "optMechmode"
   | optProgParams    => "optProgParams"
   | optProgParams1   => "optProgParams1"
   | optProgParams2  => "optProgParams2"
   | param            => "param"
   | paramList        => "paramList"
   | paramList1       => "paramList1"
   | paramList2      => "paramList2"
   | procDecl         => "procDecl"
   | progParam        => "progParam"
   | progParamList    => "progParamList"
   | program          => "program"
   | program1         => "program1"
   | stoDecl          => "stoDecl"
   | term1            => "term1"
   | term11           => "term11"
   | term2            => "term2"
   | term21           => "term21"
   | term3            => "term3"
   | term31           => "term31"
   | typedIdent       => "typedIdent"

val string_of_gramsym = (string_of_term, string_of_nonterm)

local
  open FixFoxi.FixFoxiCore
in

val productions =
[
( program, [[ T PROGRAM, T IDENT, N progParamList, N program1, T DO, N cpsCmd, T ENDPROGRAM]]),
( program1,[[ T GLOBAL, N cpsDecl],[]]),
( decl, [[ N stoDecl ],[ N funDecl ],[ N procDecl]]),
( stoDecl, [[ T CHANGEMODE, N typedIdent ],[ N typedIdent]]),
( funDecl, [[ T FUN, T IDENT, N paramList, T RETURNS, N stoDecl, N globalGlobImps, N localCpsStoDecl, T DO, N cpsCmd, T ENDFUN]]),
( globalGlobImps, [[ T GLOBAL, N globImps],[]]),
( localCpsStoDecl, [[ T LOCAL, N cpsStoDecl ],[]]),
( procDecl, [[ T PROC, T IDENT, N paramList, N globalGlobImps, N localCpsStoDecl, T DO, N cpsCmd, T ENDPROC]]),
( globImps, [[ N globImp, N globImps1 ]]),
( globImps1, [[ T COMMA, N globImp, N globImps1 ],[]]),
( globImp, [[ N optFlowmode, N optChangemode, T IDENT ]]),
( optFlowmode, [[ T FLOWMODE],[]]),
( optChangemode, [[ T CHANGEMODE],[]]),
( optMechmode, [[ T MECHMODE],[]]),
( cpsDecl, [[ N decl, N cpsDecl1]]),
( cpsDecl1, [[ T SEMICOLON, N cpsDecl ],[]]),
( cpsStoDecl, [[ N stoDecl, N cpsStoDecl1]]),
( cpsStoDecl1, [[ T SEMICOLON, N cpsStoDecl ],[]]),
( progParamList, [[ T LPAREN, N optProgParams, T RPAREN]]),
( optProgParams, [[ N optProgParams1],[]]),
( optProgParams1, [[ N progParam, N optProgParams2]]),
( optProgParams2, [[ T COMMA, N optProgParams],[]]),
( progParam, [[ N optFlowmode, N optChangemode, N typedIdent ]]),
( paramList, [[ T LPAREN, N paramList1, T RPAREN ]]),
( paramList1, [[ N param, N paramList2],[]]),
( paramList2, [[ T COMMA, N paramList1],[]]),
( param, [[ N optFlowmode, N optMechmode, N optChangemode, N typedIdent]]),
( typedIdent, [[ T IDENT, T COLON, T ATOMTYPE]]),
( cmd, [[ T SKIP],[ N expr, T BECOMES, N expr],[ T IF, N expr, T THEN, N cpsCmd, T ELSE, N cpsCmd, T ENDIF ],[ T WHILE, N expr, T DO, N cpsCmd, T ENDWHILE ],[ T CALL, T IDENT, N exprList, N optGlobInits ],[ T DEBUGIN, N expr ],[ T DEBUGOUT, N expr]]),
( optGlobInits, [[ N globInits ],[]]),
( cpsCmd, [[ N cmd, N cpsCmd1]]),
( cpsCmd1, [[ T SEMICOLON, N cpsCmd],[]]),
( globInits, [[ T INIT, N idents]]),
( idents, [[ T IDENT, N idents1]]),
( idents1, [[ T COMMA, N idents],[]]),
( expr, [[ N term1, N expr1]]),
( expr1, [[ T BOOLOPR, N term1, N expr1],[]]),
( term1, [[ N term2, N term11]]),
( term11, [[ T RELOPR, N term2 ],[]]),
( term2, [[ N term3, N term21]]),
( term21, [[ T ADDOPR, N term3, N term21],[]]),
( term3, [[ N factor, N term31 ]]),
( term31, [[ T MULTOPR, N factor, N term31],[]]),
( factor, [[ T LITERAL],[ T IDENT, N ident1 ],[ N monadicOpr, N factor],[ T LPAREN, N expr, T RPAREN]]),
( ident1, [[ T INIT],[ N exprList ],[]]),
( monadicOpr, [[ T NOT ],[ T ADDOPR]]),
( exprList, [[ T LPAREN, N exprList1, T RPAREN]]),
( exprList1, [[ N expr, N exprList2],[]]),
( exprList2, [[ T COMMA, N exprList1 ],[]])
]

val S = program

val result = fix_foxi productions S string_of_gramsym

end (* local *)
