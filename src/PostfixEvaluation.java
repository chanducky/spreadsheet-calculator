

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Stack;

/**
 * @author chandrakumar
 *
 */
public class PostfixEvaluation {

	LinkedHashMap<String,String> sheet=null;
	HashMap<String,Double> memo=null;


	/**
	 * @param sheet
	 */
	public PostfixEvaluation(LinkedHashMap<String, String> sheet) {
		super();
		this.sheet = sheet;
		memo = new HashMap<>();

	}

	/**
	 * 
	 */
	public  void evaluateSheet() {
		for(String key: sheet.keySet()){
			// print row wise evaluated value.
			System.out.println(String.format("%.5f",evaluate(sheet.get(key))));
		}
	}

	/**
	 * Method evaluate value of postfix expression.
	 * @param postfix
	 * @return Double
	 */
	private  Double evaluate(String postfix) {
		Stack<Double> stack = new Stack<Double>();
		String[] tokens = postfix.split(" ");

		int N = tokens.length;

		for (int i = 0; i < N; i++) {
			String token = tokens[i];

			if (isOperator(token)) {

				switch (token.trim().charAt(0)) {
				case '+': stack.push(stack.pop() + stack.pop());     break;
				case '*': stack.push(stack.pop() * stack.pop());     break;
				case '-': stack.push(-stack.pop() + stack.pop());    break;
				case '/': stack.push(1 / stack.pop() * stack.pop()); break;
				}
			} else if(sheet.containsKey(token)) {
				Double cellValue = null;

				if(memo.containsKey(token)){
					cellValue = memo.get(token);
				}else{
					// check if expression contains cyclic call then stop the process. 
					if(! DetectCyclicCall.isCyclic(sheet, token)){
						cellValue =evaluate(sheet.get(token));	
					}else{
						System.out.println("Cyclic call found.");
						System.exit(0);
					}

					memo.put(token, cellValue);
				}
				stack.push(cellValue);

			}else{
				try{
					stack.push(Double.parseDouble(token));
				}catch (Exception e) {
					System.out.println("Invalid input ="+token +" found.");
					System.exit(0);
				}

			}
		}

		if (!stack.isEmpty()){
			return stack.pop();
		}else{
			return 0.0;
		}
	}

	/**
	 * @param token
	 * @return boolean
	 */
	private static boolean isOperator(String token) {
		char ch=token.trim().charAt(0);
		return ch == '*' || ch == '/' || ch == '+' || ch == '-';
	}

}
