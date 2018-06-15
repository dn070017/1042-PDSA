
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * 1042 PDSA hw09_Expression
 *
 * @author Robert
 */
public class Expression {

    private Node root;
    private ArrayList<Node> prefix_answer = null;
    private ArrayList<Node> postfix_answer = null;
    private String[] dataArr;

    // DO NOT MODIFY THIS

    public Expression() {
    }

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix) {
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
        for (int i = 0; i < string.length(); i++) {
            boolean isoperater = false;
            for (int j = 0; j < operator.size(); j++) {
                if (string.charAt(i) == operator.get(j)) {
                    isoperater = true;
                    if (n != 0) {
                        data.add(string.substring(i - n, i));
                        n = 0;
                    }
                    data.add(string.substring(i, i + 1));
                }
            }
            if (isoperater == false) {
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

        Stack<String> s = new Stack<String>();
        Stack<Node> tmpStack = new Stack<Node>();

        for (int i = 0; i < dataArr.length; i++) {
            if ("")"".equals(dataArr[i]) && ""("".equals(dataArr[i-1])){
                throw new NoSuchElementException();
            }
            else if ("")"".equals(dataArr[i])) {
                Node tmp1 = tmpStack.pop();
                Node tmp2 = tmpStack.pop();
                String operat = s.pop();
                Node tmp = new Node(tmp2, tmp1, operat);
                tmpStack.push(tmp);
                root = tmp;
            } else if (op.contains(dataArr[i])) {
                s.push(dataArr[i]);
            } else if (""("".equals(dataArr[i])) {
            } else {
                Node tmp = new Node(null, null, dataArr[i]);
                tmpStack.push(tmp);
            }
        }
        return (root);
    }

    public Node[] PrintPrefix() {
        prefix_answer = new ArrayList<>();
        preOrder(root);
        Node[] prefix = new Node[prefix_answer.size()];
        prefix = prefix_answer.toArray(prefix);
        return prefix;
    }

    private void preOrder(Node a) {
        if (a != null) {
            prefix_answer.add(a);
            preOrder(a.getLeft());
            preOrder(a.getRight());
        }
    }

    public Node[] PrintPostfix() {
        postfix_answer = new ArrayList<>();
        postOrder(root);
        Node[] postfix = new Node[postfix_answer.size()];
        postfix = postfix_answer.toArray(postfix);
        return postfix;
    }

    private void postOrder(Node a) {
        if (a != null) {
            postOrder(a.getLeft());
            postOrder(a.getRight());
            postfix_answer.add(a);
        }
    }

    public double Evaluation() {
        double answer = 0;
        // hw03_Calculator
        // Calculator
        String[] data = dataArr;
        String[] tmp = new String[5];
        Stack<String> s = new Stack<String>();
        for (int i = 0; i < data.length; i++) {
            s.push(data[i]);
            if ("")"".equals(s.peek())) {
                for (int j = 0; j < 5; j++) {
                    tmp[j] = s.pop();
                }
                double num1 = Double.parseDouble(tmp[3]);
                double num2 = Double.parseDouble(tmp[1]);
                switch (tmp[2]) {
                    case ""+"":
                        answer = num1 + num2;
                        break;
                    case ""-"":
                        answer = num1 - num2;
                        break;
                    case ""*"":
                        answer = num1 * num2;
                        break;
                    case ""/"":
                        answer = num1 / num2;
                        break;
                }
                s.push(String.valueOf(answer));
            }
        }
        return (answer);
    }

    // testing
    public static void main(String[] args) {
//        String a = ""(4+(((4*2)/2)/3))"";
//        String b = ""(60*(((4*2)/2)*3))"";
        String a = ""()"";
        String c = ""(((2+3)/(4+5))+((2+3)+(4+5)))"";
        Expression cct = new Expression();
        Node result = cct.Infix2BT(a);
        Node[] test = cct.PrintPostfix();

        result = cct.Infix2BT(c);

//        Node[] test2 = cct.PrintPostfix();
//        StdOut.println(test[0].getValue());
//        StdOut.println(test[1].getValue());
//        StdOut.println(test[2].getValue());
//        StdOut.println(test[3].getValue());
//        StdOut.println(test[4].getValue());
//        StdOut.println(test[5].getValue());
//        StdOut.println(test[6].getValue());
//        StdOut.println(test[7].getValue());

        
//        StdOut.println(test2[0].getValue());
//        StdOut.println(test2[1].getValue());
//        StdOut.println(test2[2].getValue());
//        StdOut.println(test2[3].getValue());
//        StdOut.println(test2[4].getValue());
//        StdOut.println(result.getValue());
//        StdOut.println(result.getLeft().getValue());
//        StdOut.println(result.getRight().getValue());
//        StdOut.println(result.getLeft().getLeft().getValue());
//        StdOut.println(result.getRight().getRight().getValue());

    }

}

