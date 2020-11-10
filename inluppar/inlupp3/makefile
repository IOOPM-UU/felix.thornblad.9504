all:
	mkdir -p classes
	javac -d classes *.java
	javac -d classes org/ioopm/calculator/*/*.java  
	javac -d classes/org/ioopm/calculator org/ioopm/calculator/*.java

run:
	java -cp classes/org/ioopm/calculator Calculator
	
test:
	java -cp classes/org/ioopm/calculator Test

clean:
	rm -rf classes