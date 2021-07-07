grammar Vector ;

program:
    (stat ';' )* EOF 
    ;

stat: assignment | show
    ;

assignment:
    expr '->' ID
    ;

show:
    'show' expr
    ;

expr:
      op=('+'|'-') expr                  #ExprUnario
    | expr op=('+'|'-') expr             #ExprSumSub
    | '(' expr ')'                       #ExprParent
    | expr '.' expr                      #ExprProdInter
    | expr '*' expr                      #ExprMult
    |'[' expr ( ',' expr)* ']'           #ExprVector
    | ID                                 #ExprID
    | NUMBER                             #ExprNumber
    ;

ID : [a-z]+[0-9]* ;
NUMBER : [0-9]+ ('.'[0-9]+)? ;
COMMENT: '#' .*? '\n' -> skip ;
WS : [ \t\r\n]+ -> skip ;
ERROR: .;
