filepath := org/ioopm/calculator
testAdditions := classes:/usr/share/java/hamcrest/core-1.1.jar:/usr/share/java/junit4-4.5.jar:.

all:
	mkdir -p classes
	javac -d classes $(filepath)/*/[^T]*.java  
	javac -d classes $(filepath)/[^T]*.java

run:
	java -cp classes/$(filepath) Calculator
	
test:
	javac -d classes -cp $(testAdditions) $(filepath)/TestTest.java
	java -cp $(testAdditions) org.junit.runner.JUnitCore org.ioopm.calculator.TestTest

clean:
	rm -rf classes