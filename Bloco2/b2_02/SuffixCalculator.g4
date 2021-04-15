grammar SuffixCalculator ;         //zero or more repetitions of stat
program:
    stat * EOF
    ;
stat:
    expr? NEWLINE
    ;                               //optative expr
expr:
    expr expr op=('*'|'/'|'+'|'-')  #ExprSuffix 
    | Number                        #ExprNumber
    
    ;                                        
Number: [0-9]+('.'[0-9]+)?;
NEWLINE: '\r'? '\n';
WS: [ \t]+-> skip;