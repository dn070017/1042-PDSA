//import edu.princeton.cs.algs4.*;

public class Calculator{
	Stack<String> operatorStack;
	Stack<Double> valueStack;
	public Calculator(){
		operatorStack = new Stack<String>();
		valueStack = new Stack<Double>();
	}

	public Double ans (String e){
		String[] input = e.split("" "");

		for(int i = 0; i < input.length; i++){
			if(input[i].equals(""("")){
			}
			else if (input[i].equals(""+"")) {
				operatorStack.push(input[i]);
			}
			else if(input[i].equals(""-"")){
				operatorStack.push(input[i]);
			}
			else if (input[i].equals(""*"")) {
				operatorStack.push(input[i]);
			}
			else if (input[i].equals(""/"")) {
				operatorStack.push(input[i]);
			}
			else if (input[i].equals("")"")) {
				String operator = operatorStack.pop();
				if(operator.equals(""+""))
					valueStack.push(valueStack.pop() + valueStack.pop());
				else if(operator.equals(""-"")){
					double a;
					double b;
					a = valueStack.pop();
					b = valueStack.pop();
					valueStack.push(b - a);
				}
				else if(operator.equals(""*""))
					valueStack.push(valueStack.pop() * valueStack.pop());
				else if(operator.equals(""/"")){
					double a;
					double b;
					a = valueStack.pop();
					b = valueStack.pop();
					valueStack.push(b / a);
				}
			}
			else{
				valueStack.push(Double.parseDouble(input[i]));
			}
		}

		return valueStack.pop();
	}
}

/*class Stack<Item>{
	private Node first = null;

	private class Node{
		Item item;
		Node next;
	}

	public void push(Item item){
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
	}

	public Item pop(){
		Item item = first.item;
		first = first.next;
		return item;
	}
}*/
