package source;

public class Driver {

	public static void main(String[] args) {
		
		Calculator x = new Calculator();
		
		//Enter expression
		//MUST hard code VALID expression
		
		//example below
		
		System.out.println(x.evaluatePostfix(x.convertToPostfix("( 10 - 2 ) / ( 2 * 2 )")));

	}

}
