
public class Expression {

    private Node root;
    public int size = 1;
    public int opsize = 0;
    public int pcounter = 0;
    public Node[] p;

    // DO NOT MODIFY THIS
    public Expression() {
    }

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix) {
        int lengthb = infix.length();
        int counter0 = 0;
        int counter1 = 0;
        int sidelo = 0;
        char[] weib = infix.toCharArray();
        for (int i = 0; i < lengthb; i++) {
            if (weib[i] == ')') {
                counter0++;
            }
        }
        opsize = counter0 * 2 + 1;
        String[] wei = new String[counter0 * 4 + 1];
        for (int i = 0; i < lengthb; i++) {
            if (weib[i] == '(' || weib[i] == ')' || weib[i] == '+' || weib[i] == '-' || weib[i] == '*' || weib[i] == '/') {
                wei[counter1] = String.valueOf(weib[i]);
                counter1++;
            } else if (weib[i - 1] == '(' || weib[i - 1] == ')' || weib[i - 1] == '+' || weib[i - 1] == '-' || weib[i - 1] == '*' || weib[i - 1] == '/') {
                sidelo = i;
                if (weib[i + 1] == '(' || weib[i + 1] == ')' || weib[i + 1] == '+' || weib[i + 1] == '-' || weib[i + 1] == '*' || weib[i + 1] == '/') {
                    wei[counter1] = String.valueOf(weib[i]);
                    counter1++;
                }
            } else if (weib[i + 1] == '(' || weib[i + 1] == ')' || weib[i + 1] == '+' || weib[i + 1] == '-' || weib[i + 1] == '*' || weib[i + 1] == '/') {
                String temp = """";
                for (int j = sidelo; j < i + 1; j++) {
                    temp = temp + String.valueOf(weib[j]);
                }
                wei[counter1] = temp;
                counter1++;
            }
        }
        int length = wei.length;
        Stack<String> ops = new Stack<String>();
        Stack<Node> vals = new Stack<Node>();
        for (int i = 0; i < length; ++i) {
            String s = wei[i];
            //System.out.println(s);
            if (s.equals(""("")); else if (s.equals(""+"")) {
                ops.push(s);
            } else if (s.equals(""-"")) {
                ops.push(s);
            } else if (s.equals(""*"")) {
                ops.push(s);
            } else if (s.equals(""/"")) {
                ops.push(s);
            } else if (s.equals("")"")) {
                size += 2;
                Node right = vals.pop();
                Node left = vals.pop();
                Node feng = new Node(left, right, ops.pop());
                root = feng;
                vals.push(feng);

            } else {
                vals.push(new Node(null, null, s));
            }
        }

        return root;
    }

    public Node[] PrintPrefix() {
        Node[] prefix = new Node[size];
        int order = 0;
        if (root == null) {
            throw new NullPointerException();
        }
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node n = stack.pop();
            prefix[order] = n;
            order++;
            if (n.getRight() != null) {
                stack.push(n.getRight());
            }
            if (n.getLeft() != null) {
                stack.push(n.getLeft());
            }
        }
        return prefix;
    }

    public void postOrder(Node input) {
        if (input != null) {
            postOrder(input.getLeft());
            postOrder(input.getRight());
            p[pcounter] = input;
            pcounter++;
        }
    }

    public Node[] PrintPostfix() {
        Node[] postfix = new Node[size];
        if (root == null) {
            throw new NullPointerException();
        }
        //p = null;
        pcounter = 0;
        p = new Node[size];
        postOrder(root);
        for (int i = 0; i < size; i++) {
            postfix[i] = p[i];
        }
        return postfix;
    }

    public double Evaluation() {
        double answer = 0;
        if (root == null) {
            throw new NullPointerException();
        }
        Node[] value = PrintPostfix();
        Stack<String> node = new Stack<String>();
        String v1;
        String v2;
        String v3;
        double an;
        for (int i = 0; i < size; i++) {
            String s = value[i].getValue();
            if (s.equals(""+"")) {
                v2 = node.pop();
                v1 = node.pop();
                an = Double.parseDouble(v1) + Double.parseDouble(v2);
                v3 = String.valueOf(an);
                node.push(v3);
            } else if (s.equals(""-"")) {
                v2 = node.pop();
                v1 = node.pop();
                an = Double.parseDouble(v1) - Double.parseDouble(v2);
                v3 = String.valueOf(an);
                node.push(v3);
            } else if (s.equals(""*"")) {
                v2 = node.pop();
                v1 = node.pop();
                an = Double.parseDouble(v1) * Double.parseDouble(v2);
                v3 = String.valueOf(an);
                node.push(v3);
            } else if (s.equals(""/"")) {
                v2 = node.pop();
                v1 = node.pop();
                an = Double.parseDouble(v1) / Double.parseDouble(v2);
                v3 = String.valueOf(an);
                node.push(v3);
            }else{
                node.push(s);
            }

        }
        answer = Double.parseDouble(node.peek());
        return answer;
    }

}

