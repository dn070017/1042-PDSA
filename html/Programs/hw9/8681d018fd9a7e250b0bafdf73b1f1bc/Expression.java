import java.util.ArrayList;
import java.util.Stack;
import java.util.stream.Collector;

/**
.
 */
public class Expression {
    private Node root;

    // DO NOT MODIFY THIS
    public Expression() {
    }

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix) {

        Stack<Node> symbol = new Stack<>();
        Stack<Node> values = new Stack<>();
        String vals = """";

        for (int i = 0; i < infix.length(); i++) {
            char temp = infix.charAt(i);
            if ((temp == '+') || (temp == '-') || (temp == '*') || (temp == '/')) {
                if (!vals.equals("""")) {
                    Node n = new Node(null, null, vals);
//                    System.out.println(n.getValue());
                    values.push(n);
                    vals = """";
                }
                String ops = temp + """";
                Node n2 = new Node(null, null, ops);
//                    System.out.println(n2.getValue());
                symbol.push(n2);

            } else if (temp == ')') {
                if (!vals.equals("""")) {

                    Node n = new Node(null, null, vals);
//                    System.out.println(n.getValue());
                    values.push(n);
                    vals = """";
                }
                Node rootTemp = symbol.pop();
//                    System.out.println(rootTemp.getValue());
                Node rightTemp = values.pop();
//                    System.out.println(rightTemp.getValue());
                rootTemp.setRight(rightTemp);
                Node leftTemp = values.pop();
//                    System.out.println(leftTemp.getValue());
                rootTemp.setLeft(leftTemp);
                values.push(rootTemp);
                root = rootTemp;
            } else if (temp != '(') {
                vals = vals + """" + temp;
            }
        }
        return root;
    }

    public Node[] PrintPrefix() {
        if (root == null) {
            throw new NullPointerException();
        }
        Node point = root;
        Stack<Node> rightVal = new Stack<>();
        ArrayList<Node> prefixTemp = new ArrayList<>();

        while (true) {
            prefixTemp.add(point);
            if (point.getLeft() != null) {
                if (point.getRight() != null) {
                    rightVal.push(point.getRight());
                }
                point = point.getLeft();
            } else {
                if (rightVal.isEmpty()) {
                    break;
                }
                point = rightVal.pop();
            }
        }
        Node[] prefix = new Node[prefixTemp.size()];
        for (int i = 0; i < prefixTemp.size(); i++) {
            prefix[i] = prefixTemp.get(i);
        }
        return prefix;
    }


    public Node[] PrintPostfix() {
        if (root == null) {
            throw new NullPointerException();
        }
        Node point = root;
        Node lastStop = null;
        Node peek;
        Stack<Node> pos = new Stack<>();
        ArrayList<Node> postfixTemp = new ArrayList<>();

        while (!pos.isEmpty() || point != null) {

            if (point != null) {
                pos.push(point);
                point = point.getLeft();

            } else {
                peek = pos.peek();
                if (peek.getRight() == null || peek.getRight() == lastStop) {
                    postfixTemp.add(peek);
                    lastStop = pos.pop();
                } else {
                    point = peek.getRight();
                }
            }
        }

//        for (Node e : postfixTemp) {
//            System.out.print(e.getValue() + "" "");
//        }


        Node[] postfix = new Node[postfixTemp.size()];

        for (int i = 0; i < postfixTemp.size(); i++) {
            postfix[i] = postfixTemp.get(i);
        }

        return postfix;
    }


    public double Evaluation() {
        if (root == null) {
            throw new NullPointerException();
        }
        Node[] postStr = PrintPostfix();
        Stack<Double> vals = new Stack<>();
        double vals1, vals2;

        for (int i = 0; i < postStr.length; i++) {
            String n = postStr[i].getValue();
            if (n.equals(""+"")) {
                vals1 = vals.pop();
                vals2 = vals.pop();
                vals.push(vals2 + vals1);

            } else if (n.equals(""-"")) {
                vals1 = vals.pop();
                vals2 = vals.pop();
                vals.push(vals2 - vals1);

            } else if (n.equals(""*"")) {
                vals1 = vals.pop();
                vals2 = vals.pop();
                vals.push(vals2 * vals1);

            } else if (n.equals(""/"")) {
                vals1 = vals.pop();
                vals2 = vals.pop();
                vals.push(vals2 / vals1);

            } else {
                vals.push(Double.parseDouble(n));
            }
        }


        double answer = vals.pop();
        return answer;
    }
}

