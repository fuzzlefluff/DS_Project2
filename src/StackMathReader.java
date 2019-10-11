import java.util.Scanner;


public class StackMathReader {
	
	//setup a String var to hold our input, we default it to an error message in the event no input is given
	static String inputS="***nothing was entered! did you run the readEquation() method first?***";
	
	//A Method that takes an input and checks to make sure it only contains valid characters
	public static void readEquation()
	{
		//setup a scanner object to grab our string
		Scanner myScanner = new Scanner(System.in);
		//prompt the user for input
		System.out.println("Please type your 4 function math problem and press enter");
		//save the input
		inputS = myScanner.nextLine();
		//Check to see if the equation contains only valid characters
		if(inputS.matches("[abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890()+-/*]*") == false) 
			{
				System.out.println("Input contained an invalid character, please try again");
				readEquation();
			}
		//Check to see if the equation does not have any missing parentheses
		if(checkParentheses(inputS) == false )
		{
			System.out.println("Input contained invalid paranthese, please try again");
			readEquation();
		}
		//Check to see if the operators have numbers on either side of them
		if(checkOperators(inputS)== false)
		{
			System.out.println("Input had an operator issue");
			readEquation();
		}
		myScanner.close();
	}
	
	public static void convertEquation()
	{
		//Create a stack to do our conversion with
		myStack<Character> stack = new myStack<Character>(inputS.length());
		//Create a string that will be our output
		String tempS ="";
		//Scan through the input string
		for(int i=0; i<inputS.length(); i++)
		{
			//If we are on the last character, we need to dump the operator stack to our output
			if(i==inputS.length()-1) 
			{
				//make sure we print the last character unless it is a parentheses
				if(inputS.charAt(i) != ')') {tempS += inputS.charAt(i);}
				//iterate through the stack
				for(int c=stack.getStackSize(); c>0; c--) 
				{
					//grab the stack's character so we can test it
					char tempC = stack.pop();
					//send the operator to output unless it is a parentheses
					if(tempC == '+' || tempC == '-' || tempC == '*' || tempC == '/') 
					{
						tempS += tempC;
					}	
				}
				//end the cycle since we are on the last character
				break;
			}
			//check to see if our character is an operator
			if(isOperator(inputS.charAt(i))) 
			{
				//if this is the first operator we find, we simply push it
				if(stack.getStackSize() == 0) {stack.push(inputS.charAt(i));}
				else
				{
					//if the operator is  not a right parentheses, we simply push it
					if(inputS.charAt(i) == '(') {stack.push(inputS.charAt(i));}
					if(inputS.charAt(i) == '*' || inputS.charAt(i) == '/') {stack.push(inputS.charAt(i));}
					if(inputS.charAt(i) == '+' || inputS.charAt(i) == '-') {stack.push(inputS.charAt(i));}
					//if the operator is a right parentheses we dump the operator stack to output
					if(inputS.charAt(i) == ')') 
					{
						for(int c=stack.getStackSize(); c>0; c--) 
						{
							char tempC = stack.pop();
							if(tempC == '(') {break;}
							else {tempS += tempC;}
						}
					}
				}
			}
			//if the character is simply an operand, we send it to output
			if(isOperand(inputS.charAt(i)))
			{
				tempS += inputS.charAt(i);
			}
			
		}
		//lastly we update the stored string to the new postfix output
		inputS = tempS;
	}
	//A Simple method that returns whatever our currently held string is
	public static String writeEquation()
	{
		return inputS;
	}
	//A simple method that checks to see if the given string consists only of operators
	private static boolean isOperator(char iString)
	{
		String temp ="";
		temp += iString;
		if(temp.matches("[+-/*()]*")) {return true;}
		else {return false;}
	}
	//A simple method that checks to see if the given string consists only of operands
	private static boolean isOperand(char iString)
	{
		String temp ="";
		temp +=iString;
		if(temp.matches("[abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890]*")) {return true;}
		else {return false;}
	}
	//a simple method that checks to see if every open parentheses has a closed parentheses
	private static boolean checkParentheses(String iString)
	{
		//A counter to keep track of our parentheses
		int pCounter = 0;
		//Scan through the string
		for (int i = 0; i < iString.length(); i++) 
		{
			//if we find an open parentheses we increment the counter
		    if (iString.charAt(i) == '(') {pCounter++;} 
		    //if we find a closed parentheses we decrement the counter
		    else if (iString.charAt(i) == ')') {pCounter--;}
		    //if the counter doesn't balance out we return to catch the situation where )( is entered
		    if (pCounter < 0) {return false;}
		}
		//if the counter remains at zero than our input is balanced out and we return true
		if (pCounter == 0) {return true;} 
		//if the counter is not at zero the input is not balanced and we return false
		else {return false;}
	}
	//A Simple method that makes sure two operator characters are not next to each other
	private static boolean checkOperators(String iString)
	{
		//check for a situation where the input starts with a parentheses followed by an operator
		if (iString.charAt(0)=='(')
		{
			if(iString.charAt(1) == '+' || iString.charAt(1) == '-' || iString.charAt(1) == '*' || iString.charAt(1) == '/')
			{
				return false;
			}
		}
		//check for a situation where the input ends with an operator followed by a parentheses
		if(iString.charAt(iString.length()-1) == ')')
		{
			if(iString.charAt(iString.length()-2) == '+' || iString.charAt(iString.length()-2) == '-' || iString.charAt(iString.length()-2) == '*' || iString.charAt(iString.length()-2) == '/')
			{
				return false;
			}
		}
		//if our string starts with an operator we return false
		if(iString.charAt(0) == '+' || iString.charAt(0) == '-' || iString.charAt(0) == '*' || iString.charAt(0) == '/')
		{
			return false;
		}
		//if our string ends with an operator we return false
		if(iString.charAt(iString.length()-1) == '+' || iString.charAt(iString.length()-1) == '-' || iString.charAt(iString.length()-1) == '*' || iString.charAt(iString.length()-1) == '/')
		{
			return false;
		}
		//Scan through the input string
		for(int i=0; i<iString.length()-1; i++)
		{
			//if we find an operator character, we will check the next one
			if(iString.charAt(i) == '+' || iString.charAt(i) == '-' || iString.charAt(i) == '*' || iString.charAt(i) == '/')
			{
				// if next string is also an operator we have an error
				if(iString.charAt(i+1) == '+' || iString.charAt(i+1) == '-' || iString.charAt(i+1) == '*' || iString.charAt(i+1) == '/')
				{
					return false;
				}
			}
		}
		//if everything checks out we return true
		return true;
	}

}
