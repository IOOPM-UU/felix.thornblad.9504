main: hash_table

C_COMPILER			= gcc
C_OPTIONS				= -Wall -pedantic -ggdb
C_LINK_OPTIONS	= -lm 
CUNIT_LINK			= -lcunit

%.o:  %.c
	$(C_COMPILER) $(C_OPTIONS) $? -c
	
test_hash: hash_table_tests.o
	gcc -ggdb -Wall -std=c11 hash_table_tests.c -o test_hash -lcunit
	
myprog.final: file1.o file2.o file3.o
# TODO: add e.g. optimisation flags, remove unnecessary linking, etc.

clean:
	rm -f *.o test_hash myprog

test: test_hash
	./test_hash

memtest: test_hash
	valgrind --leak-check=full ./test_hash