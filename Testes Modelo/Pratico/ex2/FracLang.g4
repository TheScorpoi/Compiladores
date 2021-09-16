grammar FracLang ;

program :
    (stat ';')* EOF
    ;

stat :
    display | assignment
    ;

expr:
     op=('+'|'-') expr              #ExprUnario
    | expr op=('*'|':') expr        #ExprMultDiv
    | expr op=('+'|'-') expr        #ExprSomaSub
    | '(' expr ')'                  #ExprParen
    | 'reduce' expr                 #ExprReduce
    | NUMBER '/' NUMBER             #ExprFraction
    | NUMBER                        #ExprNumber
    | ID                            #ExprID
    ;

display :
    'display' expr
    ;

assignment : 
    ID '<=' expr
    ;

NUMBER : [0-9]+ ;
ID : [a-z]+ ;
COMMENT: '--' .*? '\n' -> skip ;
WS: [ \t\r\n]+ -> skip;

