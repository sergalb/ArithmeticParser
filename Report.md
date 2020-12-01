#Report for lab #2: Manual construction of top-down parsers

##Grammar for arithmetic expressions
Expr &rarr; Add\
Add &rarr; Mul Add'\
Add' &rarr; (+|-) Mul Add'\
Add' &rarr; eps \
Mul &rarr; Unary Mul' \
Mul' &rarr; (*|/) Mul' \
Mul' &rarr; eps \
Unary &rarr; Unary' Num \
Unary' -> - Unary' \
Unary' -> eps \
Num &rarr; dig \
Num &rarr; (Expr)


##First and Follow



