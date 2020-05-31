package source;



public class Calculator {
	
	public Calculator() {}
	
	public String[] convertToPostfix(String infix) {
		Stack<String> stack = new Stack<String>();
		String infixParsed[] = infix.split(" ");
		int postfixLen = 0;
		for(int i = 0; i < infixParsed.length; i++) {
			if(!infixParsed[i].equals("(") && !infixParsed[i].equals(")")) {
				postfixLen++;
			}
		}
		String postfix[] = new String[postfixLen];
		int counter = 0;
		int postfixIndex = 0;
		while(counter < infixParsed.length) {
			String currentCharacter = infixParsed[counter];
			switch(currentCharacter) {
			case "^":
				stack.push(currentCharacter);
				break;
			case "+": case "-": case "*": case "/":
				while(!stack.isEmpty() && ((currentCharacter.equals("*") && stack.peek().equals("*")) || 
						(currentCharacter.equals("/") && stack.peek().equals("/")) ||
						(currentCharacter.equals("-") && stack.peek().equals("-")) ||
						(currentCharacter.equals("+") && stack.peek().equals("+")) ||
						(currentCharacter.equals("-") && stack.peek().equals("/")) ||
						(currentCharacter.equals("-") && stack.peek().equals("*")) ||
						(currentCharacter.equals("+") && stack.peek().equals("/")) ||
						(currentCharacter.equals("+") && stack.peek().equals("*")))) {
					
					currentCharacter = stack.peek();
					postfix[postfixIndex] = currentCharacter;
					postfixIndex++;
					stack.pop();
				}
				stack.push(currentCharacter);
				break;
			case "(":
				stack.push(currentCharacter);
				break;
			case ")":
				while(!stack.peek().equals("(")) {
					currentCharacter = stack.pop();
					postfix[postfixIndex] = currentCharacter;
					postfixIndex++;
				}
				stack.pop();
				break;
			case "[":
				stack.push(currentCharacter);
				break;
			case "]":
				while(!stack.peek().equals("(")) {
					currentCharacter = stack.pop();
					postfix[postfixIndex] = currentCharacter;
					postfixIndex++;
				}
				stack.pop();
				break;
			case "{":
				stack.push(currentCharacter);
				break;
			case "}":
				while(!stack.peek().equals("(")) {
					currentCharacter = stack.pop();
					postfix[postfixIndex] = currentCharacter;
					postfixIndex++;
				}
				stack.pop();
				break;
			default:
				postfix[postfixIndex] = currentCharacter;
				postfixIndex++;
				break;
			}
			counter++;
		}
		while(!stack.isEmpty()) {
			postfix[postfixIndex] = stack.pop();
			postfixIndex++;
		}
		return postfix;
	}
	
	public double evaluatePostfix(String postfix[]) {
		Stack<String> stack = new Stack<String>();
		int counter = 0;
		double finalResult = 0.0;
		while(counter < postfix.length) {
			String currentCharacter = postfix[counter];
			while(currentCharacter == null) {
				counter++;
				currentCharacter = postfix[counter];
			}
			switch(currentCharacter) {
				case "(" : case ")" : case "{" : case "}" : case "[" : case "]" :
					counter++;
					break;
				case "+" : case "-" : case "*" : case "/" : case "^" :
					String operand2 = stack.pop();
					String operand1 = stack.pop();
					if(currentCharacter.equals("+")) {
						finalResult = (Double.parseDouble(operand1) + Double.parseDouble(operand2));
						counter++;
					}
					if(currentCharacter.equals("-")) {
						finalResult = (Double.parseDouble(operand1) - Double.parseDouble(operand2));
						counter++;
					}
					if(currentCharacter.equals("*")) {
						finalResult = (Double.parseDouble(operand1) * Double.parseDouble(operand2));
						counter++;
					}
					if(currentCharacter.equals("/")) {
						finalResult = (Double.parseDouble(operand1) / Double.parseDouble(operand2));
						counter++;
					}
					if(currentCharacter.equals("^")) {
						finalResult = (Math.pow(Double.parseDouble(operand1), Double.parseDouble(operand2)));
						counter++;
					}
					stack.push(Double.toString(finalResult));
					break;
				default:
					stack.push(currentCharacter);
					counter++;
					break;
			}
		}
		return Double.parseDouble(stack.peek());
	}

}
