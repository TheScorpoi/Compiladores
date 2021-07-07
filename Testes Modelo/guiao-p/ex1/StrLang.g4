grammar StrLang;

program :
    stat * EOF
    ;

stat :
    print | assignment 
    ;

expr:
      expr op=('+' | '-') expr      #ExprConRem
    | 'input' '(' TEXT ')'          #ExprInput
    | '(' expr '/' expr '/' expr ')'  #ExprSubs
    | '(' expr ')'                  #ExprParent
    | 'trim' expr                   #ExprTrim
    | TEXT                          #ExprText
    | ID                            #ExprID
    ;

print : 'print' expr ;
assignment: ID ':' expr;

TEXT: '"' .*? '"';
ID: [a-zA-Z.0-9]+ ;
INPUT : '"' .*? '"' ;
COMMENT: '//' .*? '\n' -> skip ;
WS: [ \t\r\n]+ -> skip;
