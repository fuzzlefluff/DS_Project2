
public class mainClass {

	public static void main(String[] args) {
		//Prompt the user for input
		StackMathReader.readEquation();
		//Convert the string to post-fix
		StackMathReader.convertEquation();
		//print the string to the console
		System.out.println("Your equation in postfix notation: "+StackMathReader.writeEquation());
	}

}
