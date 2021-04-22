grammar FractionalCalculator ;

program : 
    stat * EOF
    ;

stat :
      print            #ExprPrint
    | assignment       #ExprPrint
    ;
print : 
    'print' expr ';'   #PrintExpr
    ;

assignment :
    expr '->' ID ';'
    ;

expr : 
        expr op=('*'|'/') expr                  #ExprMultDiv
    |   expr op=('+'|'-')   expr                #ExprAddSub
    |   op =('+' | '-')? '(' expr ')'           #Parenthesis
    |   op =('+' | '-')? Integer                #Integer
    |   op =('+' | '-')? Integer '/' Integer    #Fraction
    |   '(' expr ')^' op =('+' | '-')? Integer  #Potency
    |   'read'                                  #Input
    |   ID                                      #Identifier
    ;

ID : [a-zA-Z]+ ;
Integer : [0-9]+ ;
NEWLINE : '\r'? '\n';
WS : [ \t]+ -> skip ;
COMMENT: '#' .*? '\n' -> skip ;
