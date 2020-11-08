
C_COMPILER     = gcc
C_OPTIONS      = -Wall -pedantic -g 
C_LINK_OPTIONS = -lm 
C_GCOV_OPTIONS = -pg -fprofile-arcs -ftest-coverage -lgcov
CUNIT_LINK     = -lcunit

ui: ui.o db.o hash_table.o list_linked.o utils.o
	$(C_COMPILER) $(C_LINK_OPTIONS) $(C_OPTIONS) ui.o db.o hash_table.o list_linked.o utils.o -o $@ 
	
gcov_ui: ui.o db.o hash_table.o list_linked.o utils.o
	$(C_COMPILER) $(C_LINK_OPTIONS) $(C_OPTIONS) $(C_GCOV_OPTIONS) $? -o $@ 
	
test: ui
	./ui < test.txt

memtest: ui
	valgrind --leak-check=full ./ui < test.txt
	
run: ui
	./ui 

memrun: ui
	valgrind --leak-check=full ./ui 
	
gcov: gcov_ui
	./gcov_ui < test.txt
	gcov -b -c db.c ui.c

clean:
	rm -f *.o ui
	rm -f *.o gcov_ui
	rm -f *.gcda 
	rm -f *.gcno 
	rm -f *.gcov 
	rm -f *.h.gch 
