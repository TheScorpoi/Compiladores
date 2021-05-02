grammar CalcFracional;

program: print* EOF | assignment;

print: ('print'+ expr)? NEWLINE;
assignment: expr '->' ID;
expr:
	<assoc = right> expr '^' expr			# ExprPotencia
	| op = ('+' | '-') Integer '/' Integer	# ExprUnitario
	| expr op = ('*' | ':' | '%') expr		# ExprMultDivMod
	| expr op = ('+' | '-') expr			# ExprAddSub
	| Integer								# ExprInteger
	| Integer '/' Integer					# ExprFraction
	| '(' expr ')'							# ExprParent
	| ID									# ExprID
	| 'reduce' expr							# ExprReduce
	| 'read "' expr '"'						# ExprRead;

ID: [a-zA-Z]+;
Integer: [0-9]+;
NEWLINE: '\r'? '\n';
WS: [ \t]+ -> skip;
COMMENT: '#' .*? '\n' -> skip;