grammar ReadNumbers ;

file : line* EOF ;
line : NUM '-' WORD NEWLINE ;

NUM : [0-9]+ ;
WORD : [a-zA-Z]+ ;
NEWLINE : '\r'? '\n';
WS : [ \t]+ -> skip;