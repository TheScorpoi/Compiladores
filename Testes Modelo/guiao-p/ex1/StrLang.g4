grammar StrLang;

program :
    stat * EOF
    ;

stat :
    print | assignment 
    ;

print : 'print' (TEXT | ID ) ;
assignment: ID ':' (TEXT | input );

input: 
    'input(' TEXT ')'
    ;

TEXT: '"' .*? '"';
ID: [a-zA-Z.0-9]+ ;
INPUT : '"' .*? '"' ;
COMMENT: '//' .*? '\n' -> skip ;
WS: [ \t\r\n]+ -> skip;
