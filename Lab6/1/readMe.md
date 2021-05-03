#Problems found On Stack:
Code smells :
	#1
	Using conditions to check if the stack is empty when we already created a function that does that
	Solution: Changing all those conditions to use the isEMpty() function
	#2
	Using if else to return boolean values when then can be easily evaluated in a more simple way
	Solution: Use "bar(param, expression, "qix");" and return the expression
	#3
	Should also update de fixme version
	#4
	No need to add the public tag in tests and we can reduce visability since we are using juni5
	Soltuion: Change the public tag to protected in these tests
