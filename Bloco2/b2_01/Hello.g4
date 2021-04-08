grammar Hello;              //Define a grammar called Hello
main : option* EOF ;
option : greetings | bye ;
greetings : 'hello' ID+ ;    //match keyboard hello follow by an identifier
bye : 'bye' ID+ ; 
ID : [a-zA-Z]+ ;               //maych lower-case identifiers
WS : [ \t\r\n]+ -> skip ;   //skip spaces, tabs, newlines...