#How to build program:

make all:    compiles the program
make test:   runs a few tests on the ast and parser
make run:    runs the program
make clean:  deletes class files

Syntax for scopes: '{' followed by 'expression' followed by '}'.
    Example: {1 = x} + {2 * 2}

Syntax for conditionals: if expression() scope() else scope().
    Example: if 1 > 0 {1} else {0}
