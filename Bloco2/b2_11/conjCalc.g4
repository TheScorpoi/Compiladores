grammar conjCalc;

program: print* EOF;
print: expr? NEWLINE;

expr:
	expr '&' expr		# ExprInterset
	| expr '\\' expr	# ExprSubtract
	| expr '+' expr		# ExprUnion
	| '(' expr ')'		# ExprParent
	| conjunto			# ExprConj
	| ID				# ExprID
	| ID '=' expr		# ExprAssign;

conjunto: '{' (elem (',' elem)*)? '}';
ID: [A-Z]+;
Word: [a-zA-Z]+;
Integer: [0-9]+;
elem: Word | Integer;
NEWLINE: '\r'? '\n';
WS: [ \t]+ -> skip;
COMMENT: '--' .*? '\n' -> skip;