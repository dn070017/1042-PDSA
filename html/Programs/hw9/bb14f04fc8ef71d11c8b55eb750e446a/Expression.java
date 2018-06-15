import java.util.ArrayList;

public class Expression{
  
    private Node root;
    
    // DO NOT MODIFY THIS
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
    	String temp2 = """";
		String[] infixarr;
    	String[] number;
    	String[] operator;
    	Node op_left;
    	Node op_right;
		for(int i = 0 ; i< infix.length();i++){
    		if(infix.charAt(i)=='(' || infix.charAt(i)==')' || infix.charAt(i)=='+' || infix.charAt(i)=='-' || infix.charAt(i)=='*' || infix.charAt(i)=='/'){
    			temp2 = temp2+ infix.charAt(i)+"""";
    			temp2 = temp2+"" "";
    		}else if(i<infix.length()-1){
    			if(Character.isDigit(infix.charAt(i+1))==false && infix.charAt(i+1)!='.'){
    				temp2 = temp2+ infix.charAt(i)+"""";
    				temp2 = temp2+"" "";
    			}else{
    				temp2 = temp2+ infix.charAt(i)+"""";
    			}
    		}
    	}
		infixarr = temp2.split("" "");
    	Stack<Node> stack_node = new Stack<Node>();
    	for(int i = 0 ; i< infixarr.length;i++){
    		if(infixarr[i].equals("")"")){
    			op_right = stack_node.pop();
    			root = stack_node.pop();
    			//System.out.println(""final op:""+op.getValue());
    			op_left = stack_node.pop();
    			stack_node.pop(); //pop ""(""
    			
    			root.setRight(op_right);
    			root.setLeft(op_left);
    			stack_node.push(root);
    			
    		}else{
    			Node node = new Node(null, null, infixarr[i]);
    			stack_node.push(node);
    		}
    	}
    	return root;
    }
    
    public Node[] PrintPrefix(){
        Node[] prefix =null;
        ArrayList<Node> prefixarrlist = new ArrayList<Node>();
        if(root==null){throw new NullPointerException(); };
        preOrder(root, prefixarrlist);
        //prefix[0] = root;
        prefix = new Node[prefixarrlist.size()];// important!! or you will have null pointer error!!
        for(int i = 0 ; i <prefixarrlist.size();i++){
        	prefix[i] = prefixarrlist.get(i);
        }    
        return prefix;
    }
    private void preOrder(Node v, ArrayList<Node> n){
    	if(v==null) return;
    	n.add(v);
    	preOrder(v.getLeft(),n);
    	preOrder(v.getRight(),n);
    }
    
    public Node[] PrintPostfix(){	
        Node[] postfix = null;
        ArrayList<Node> postfixarrlist = new ArrayList<Node>();
        if(root==null){throw new NullPointerException(); };
        postOrder(root, postfixarrlist);
        //prefix[0] = root;
        postfix = new Node[postfixarrlist.size()]; // important!! or you will have null pointer error!!
        for(int i = 0 ; i <postfixarrlist.size();i++){
        	postfix[i] = postfixarrlist.get(i);
        }    
        return postfix;
    }
    private void postOrder(Node v, ArrayList<Node> n){
    	if(v==null) return;
    	postOrder(v.getLeft(),n);
    	postOrder(v.getRight(),n);
    	n.add(v);
    }
    
    public double Evaluation(){
    	if(root==null){throw new NullPointerException(); };
    	double answer = 0;
    	double double1 = 0;
    	double double2 = 0;
    	double double_temp = 0;
        Node[] temp = PrintPostfix();
        Stack<String> stack_post = new Stack<String>();
        for(int i = 0 ; i<temp.length; i++){
        	stack_post.push(temp[i].getValue());
        	if(stack_post.peek().equals(""*"")){
				stack_post.pop();
				double1 = Double.parseDouble(stack_post.pop());
				double2 = Double.parseDouble(stack_post.pop());
				double_temp = double2*double1;
				stack_post.push(double_temp+"""");
			}if(stack_post.peek().equals(""/"")){
				stack_post.pop();
				double1 = Double.parseDouble(stack_post.pop());
				double2 = Double.parseDouble(stack_post.pop());
				double_temp = double2/double1;
				stack_post.push(double_temp+"""");
			}if(stack_post.peek().equals(""+"")){
				stack_post.pop();
				double1 = Double.parseDouble(stack_post.pop());
				double2 = Double.parseDouble(stack_post.pop());
				double_temp = double2+double1;
				stack_post.push(double_temp+"""");
			}if(stack_post.peek().equals(""-"")){
				stack_post.pop();
				double1 = Double.parseDouble(stack_post.pop());
				double2 = Double.parseDouble(stack_post.pop());
				double_temp = double2-double1;
				stack_post.push(double_temp+"""");
			}
        		
        }
        answer = double_temp;
        return answer;
    }
}

