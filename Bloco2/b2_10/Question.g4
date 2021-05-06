grammar Question;


program: question *EOF;

question: ID '(' STRING ') {' option* '}' ;

option: STRING ':' INT ';' ;

ID: [a-zA-Z.1-9]+;
INT : [0-9]+;
STRING: [a-zA-Z]+;
NEWLINE: '\r'? '\n';
WS: [ \t]+ -> skip;
COMMENT: '#' .*? '\n' -> skip;
