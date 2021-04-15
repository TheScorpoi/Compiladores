grammar PrefixCalculator ;         //zero or more repetitions of stat
program:
    stat * EOF
    ;
stat:
    expr? NEWLINE
    ;                               //optative expr
expr:
    op=('*'|'/'|'+'|'-') expr expr #ExprPrefix 
    | Number                       #ExprNumber
    
    ;                                        
Number: [0-9]+('.'[0-9]+)?;
NEWLINE: '\r'? '\n';
WS: [ \t]+-> skip;