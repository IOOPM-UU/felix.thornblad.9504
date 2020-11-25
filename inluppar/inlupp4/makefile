filepath := org/ioopm/calculator
testpath := $(filepath)/test
input := $(testpath)/input.txt
output := $(testpath)/output.txt
expected_output := $(testpath)/expected_output.txt
testAdditions := classes:/usr/share/java/hamcrest/core-1.1.jar:/usr/share/java/junit4-4.5.jar:.
.SILENT:

all:
	javac -d classes $(filepath)/[^t]*/*.java  
	javac -d classes $(filepath)/*.java

run:
	java -cp classes org.ioopm.calculator.Calculator
	
test:
	javac -d classes -cp $(testAdditions) $(filepath)/test/*.java
	java -cp $(testAdditions) org.junit.runner.JUnitCore org.ioopm.calculator.test.CreateClassTest
	java -cp $(testAdditions) org.junit.runner.JUnitCore org.ioopm.calculator.test.GetValueTest
	java -cp $(testAdditions) org.junit.runner.JUnitCore org.ioopm.calculator.test.IsConstantTest
	java -cp $(testAdditions) org.junit.runner.JUnitCore org.ioopm.calculator.test.GetNameTest
	java -cp $(testAdditions) org.junit.runner.JUnitCore org.ioopm.calculator.test.IsCommandTest
	java -cp $(testAdditions) org.junit.runner.JUnitCore org.ioopm.calculator.test.GetPriorityTest
	java -cp $(testAdditions) org.junit.runner.JUnitCore org.ioopm.calculator.test.EqualsTest
	java -cp $(testAdditions) org.junit.runner.JUnitCore org.ioopm.calculator.test.ParserTest
	java -cp classes org.ioopm.calculator.test.CalculatorTest < $(input) > $(output)
	diff $(output) $(expected_output)
	
	
clean:
	rm -rf classes
	mkdir -p classes