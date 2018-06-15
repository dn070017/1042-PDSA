
import java.util.ArrayList;


/**
 * 1042 PDSA
 * hw09_Expression
 * @author Robert
 */

public class Expression{
  
    private Node root;
    private ArrayList<Node> prefix_answer = null;
    private ArrayList<Node> postfix_answer = null;
    private String[] dataArr;
    // DO NOT MODIFY THIS
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
        // initize root
        root = null;
        String string = infix;
        // operator as +-*/()
        ArrayList<Character> operator = new ArrayList<>();
        operator.add('+');
        operator.add('-');
        operator.add('*');
        operator.add('/');
        operator.add('(');
        operator.add(')');
        ArrayList<String> data = new ArrayList<>();
        int n = 0;
        for (int i = 0; i < string.length(); i++){
            boolean isoperater = false;
            for (int j = 0; j < operator.size(); j++){
                if (string.charAt(i) == operator.get(j)){
                    isoperater = true;
                    if (n != 0){
                        data.add(string.substring(i-n, i));
                        n = 0;
                    }
                    data.add(string.substring(i, i+1));
                }
            }
            if (isoperater == false){
                n++;
            }
        }
        // dataArr (infix)
        dataArr = new String[data.size()];
        dataArr = data.toArray(dataArr);
        
//        for(String s : dataArr)
//            System.out.println(s);
        
        // set op as operator
        ArrayList<String> op = new ArrayList<>();
        op.add(""+"");
        op.add(""-"");
        op.add(""*"");
        op.add(""/"");
        
        // Node 2 tmp: a, b
        Node a = null;
        Node b = null;
        Stack<String> s = new Stack<String>();
        int count = 0;
        for (int i=0; i < dataArr.length; i++){
            if ("")"".equals(dataArr[i]) && ""("".equals(dataArr[i-4]))
                count++;
        }
        Node[] rootArr = new Node[count];
        int k = 0;
        for (int i=0; i < dataArr.length; i++){
            s.push(dataArr[i]);
            if ("")"".equals(dataArr[i]) && ""("".equals(dataArr[i-4])){
                s.pop();
                String num2 = s.pop();
                String operat = s.pop();
                String num1 = s.pop();
                s.pop();
                a = new Node(null, null, num1);
                b = new Node(null, null, num2);
                rootArr[++k] = new Node(a, b, operat);
            }
            else if ("")"".equals(dataArr[i]) && ""("".equals(dataArr[i-2])){
                s.pop();
                String tmp1 = s.pop();
                s.pop();
                if (root == null){
                    root = new Node(rootArr[k-1], rootArr[k], tmp1);
                }
                else{
					root = new Node(root, rootArr[k-1], tmp1);
				}
            }
            else if ("")"".equals(dataArr[i])){
                s.pop();
                String tmp1 = s.pop();
                String tmp2 = s.pop();
                s.pop();
                if (op.contains(tmp1)){
                    a = new Node(null, null, tmp2);
                    rootArr[k] = new Node(a, rootArr[k], tmp1);
                }
                else if(op.contains(tmp2)){
                    b = new Node(null, null, tmp1);
                    rootArr[k] = new Node(rootArr[k], b, tmp2);
                }   
            }
        }
        return(root);
    }

    public Node[] PrintPrefix(){
        preOrder(root);
//        StdOut.println(prefix_answer.size());
        Node[] prefix = new Node[prefix_answer.size()];
        prefix = prefix_answer.toArray(prefix);
        return prefix;
    }
    
    private void preOrder(Node a){
        if (a != null){
            prefix_answer = new ArrayList<>();
            prefix_answer.add(a);
            preOrder(a.getLeft());
            preOrder(a.getRight());
        }
    }
  
    public Node[] PrintPostfix(){
        postOrder(root);
        Node[] postfix = new Node[postfix_answer.size()];
        postfix = postfix_answer.toArray(postfix);
        return postfix;
    }

    private void postOrder(Node a){
        if (a != null){
            postfix_answer = new ArrayList<>();
            postfix_answer.add(a);
            postOrder(a.getLeft());
            postOrder(a.getRight());
        }
}
    
    
    public double Evaluation(){
        double answer = 0;        
        // hw03_Calculator
        // Calculator
        String[] data = dataArr;
        String[] tmp = new String[5];
        Stack<String> s = new Stack<String>();
        for (int i=0; i<data.length; i++){
            s.push(data[i]);
            if ("")"".equals(s.peek())){
                for (int j=0; j<5; j++)
                    tmp[j] = s.pop();
                double num1 = Double.parseDouble(tmp[3]);
                double num2 = Double.parseDouble(tmp[1]);
                switch (tmp[2]){
                    case ""+"":
                        answer = num1+num2;
                        break;
                    case ""-"":
                        answer = num1-num2;
                        break;
                    case ""*"":
                        answer = num1*num2;
                        break;
                    case ""/"":
                        answer = num1/num2;
                        break;
                }
                s.push(String.valueOf(answer));
            }
        }
        return(answer);
    }
    
    
    // testing
    public static void main(String[] args){
//        String a = ""(4+(((4*2)/2)/3))"";
//        String b = ""(60*(((4*2)/2)*3))"";
        String a = ""(((0.235+0.88)*7.2)+(200+10))"";
        Expression cct = new Expression();
        Node result = cct.Infix2BT(a);
//        result = cct.Infix2BT(c);
        Node[] test = cct.PrintPrefix();
//        StdOut.println(cct.Evaluation());
//        StdOut.println(test[0].getValue());
//        StdOut.println(test[1].getValue());
//        StdOut.println(test[2].getValue());
//        StdOut.println(test[3].getValue());
//        StdOut.println(test[4].getValue());
//        StdOut.println(test[5].getValue());
//        StdOut.println(test[6].getValue());
//        StdOut.println(test[7].getValue());

        StdOut.println(result.getValue());
        StdOut.println(result.getLeft().getValue());
        StdOut.println(result.getRight().getValue());
        
    }
    
}
