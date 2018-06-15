/*
.
 * To change this template file, choose Tools | Templates
.
*/
/**
 *
 * @author jerry
 */
public class Expression{
    private Node root;

    // DO NOT MODIFY THIS
    public Expression(){ 
    }

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
        char[] infixchar = infix.toCharArray();
        char[] postfixchar = postfixer(infixchar);
        Stack<Node> stack = new Stack<Node>();
		for (int i = 0; i < postfixchar.length; i++) {
			Node node = new Node(null,null,String.valueOf(postfixchar[i]));
			switch (postfixchar[i]) {
			case '0':case '1':case '2':case '3':case '4':
			case '5':case '6':case '7':case '8':case '9':
				stack.push(node);
				break;
			case '+':case '-':case '/':case '*':
				node.setRight(stack.pop());
				node.setLeft(stack.pop());
				stack.push(node);
				break;
			default:
			}
		}
        this.root = stack.pop();
        return root;
    } 


    public Node[] PrintPrefix(){
        if(root == null) throw new NullPointerException();
        Queue<Node> q = new Queue<Node>();
        PrintPrefix(root,q);
        int N = q.size();
        Node[] result = new Node[N];
        int i = 0;
        for(Node x:q){
            result[i++] = x;
        }
        return result;
    }
    
    private void  PrintPrefix(Node x,Queue<Node> q){
        if(x == null) return;
        q.enqueue(x);
        PrintPrefix(x.getLeft(),q);
        PrintPrefix(x.getRight(),q);
    }
    
  
    public Node[] PrintPostfix(){
        if(root == null) throw new NullPointerException();
        Queue<Node> q = new Queue<Node>();
        PrintPostfix(root,q);
        int N = q.size();
        Node[] result = new Node[N];
        int i = 0;
        for(Node x:q){
            result[i++] = x;
        }
        return result;        
    }
    
    private void PrintPostfix(Node x,Queue<Node> q){
        if(x == null) return;
        PrintPostfix(x.getLeft(),q);
        PrintPostfix(x.getRight(),q);
        q.enqueue(x);
    }

    public double Evaluation(){
        if(root == null) throw new NullPointerException();
        Node[] nodes = PrintPostfix();
        Stack<Double> s = new Stack<Double>();
        for(int i = 0; i < nodes.length;i++){
            String data = nodes[i].getValue();
            if(data.equals(""+"")){s.push(s.pop()+s.pop());}
            else if(data.equals(""*"")){s.push(s.pop()*s.pop());}
            else if(data.equals(""-"")){
                double first = s.pop();
                double second = s.pop();
                s.push(second-first);
            }
            else if(data.equals(""/"")){
                double first = s.pop();
                double second = s.pop();
                s.push(second/first);
            }            
            else{s.push(Double.parseDouble(data));}
        }
        Double answer = s.pop();
        return(answer);
    }
    
    public static void main(String[] args) {
        Expression test = new Expression();
        String teststr = ""((7*3+2))"";
        char[] testpost = test.postfixer(teststr.toCharArray());
        for(int i = 0; i < testpost.length;i++){
            System.out.print(testpost[i]+"" "");
        }
        System.out.println();
        test.Infix2BT(teststr);
        Node[] result = test.PrintPrefix();
        for(int i = 0; i < result.length; i++){
            System.out.print(result[i].getValue()+"" "");
        }
        System.out.println();
        Node[] result2 = test.PrintPostfix();
        for(int i = 0; i < result2.length; i++){
            System.out.print(result2[i].getValue()+"" "");
        }        
        System.out.println();
        System.out.print(test.Evaluation());
    }
    
    
    // function to convert infix to postix, convert first than create the tree
	public static char[] postfixer(char[] infix) {
		// create a new stack of character type
		Stack<Character> stack = new Stack<Character>();
		// create an array 'result' which is the same length as of infix and
		// type char
		char[] result = new char[infix.length];
		// set count as the counter for the operations on result
		// increment after every use
		int count = 0;
		for (int i = 0; i < infix.length; i++) {
			// System.out.println(result);
			switch (infix[i]) {
			case '0':case '1':case '2':case '3':case '4':
			case '5':case '6':case '7':case '8':case '9':
				result[count] = infix[i];
				count++;
				break;
			case '(':
				stack.push(infix[i]);
				break;
			case ')':
				while ( (!stack.isEmpty()) && (stack.peek() == '(')) {
					stack.pop();
				}
				if ((!stack.isEmpty())) {
					result[count] = stack.pop();
					count++;
                                        if((stack.peek() == '(')) stack.pop(); 
				}
				break;
			case '+':case '*':case '-':case '/':
				if ((!stack.isEmpty()) && (stack.peek() != '(')) {
					char checker = stack.peek();
.
					int decider = checkPrecedence(infix[i], checker);
					if (decider == 1) {
						stack.push(infix[i]);
					} 
                                        else {
						result[count] = stack.pop();
						count++;
						stack.push(infix[i]);
					}
				} 
                                else {
					stack.push(infix[i]);
				}
				break;
			default:
				System.out.println(""Invalid Data"");
			}
		}
		while ((!stack.isEmpty())) {
                        result[count] = stack.pop();
			count++;
		}
		return result;
	}

		
	public static int checkPrecedence(char a, char b) {
            int decider = 0; 
		switch (a) {
		case '/':
			switch (b) {
			case '/':decider = 0;break;
			case '*':decider = 1;break;
			case '+':decider = 1;break;
			case '-':decider = 1;break;
                        default:
			}
			break;
		case '*':
			switch (b) {
			case '/':decider = 2;break;
			case '*':decider = 0;break;
			case '+':decider = 1;break;
			case '-':decider = 1;break;
			default:
			}
			break;
		case '+':
			switch (b) {
			case '/':decider = 2;break;
			case '*':decider = 2;break;
			case '+':decider = 0;break;
			case '-':decider = 1;break;
			default:
			}
			break;
		case '-':
			switch (b) {
			case '/':decider = 2;break;
			case '*':decider = 2;break;
			case '+':decider = 2;break;
			case '-':decider = 0;break;
			default:
			}
			break;
		default:
		}
                return decider;
	}
}

    

