public class Expression {

    private Node root;
    private Node[] b;

    // DO NOT MODIFY THIS
    public Expression() {
    }

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix) {
        char[] a = infix.toCharArray();
        String[] tempb = new String[a.length];
        int arraysize = 0;
        for (int i = 0; i < a.length - 1; i++) {
            tempb[i] = infix.subSequence(i, i + 1).toString();
            int tempcount = i;
            if (tempb[i].equals(""+"") == false && tempb[i].equals(""-"") == false && tempb[i].equals(""*"") == false && tempb[i].equals(""/"") == false && tempb[i].equals(""("") == false && tempb[i].equals("")"") == false) {
                for (int j = i + 1; j < a.length - 1; j++) {
                    String tempbb = infix.subSequence(j, j + 1).toString();
                    if (tempbb.equals(""+"") == false && tempbb.equals(""-"") == false && tempbb.equals(""*"") == false && tempbb.equals(""/"") == false && tempbb.equals(""("") == false && tempbb.equals("")"") == false) {
                        tempcount = j;
                    } else {
                        break;
                    }
                }

                if (tempcount != i) {
                    tempb[i] = infix.subSequence(i, tempcount + 1).toString();
                    i = tempcount;
                }
            }
            arraysize++;
        }
        int idx = 0;
        b = new Node[arraysize + 1];
        Node temp = new Node(null, null, "")"");
        b[arraysize] = temp;
        for (int i = 0; i < tempb.length; i++) {
            if (tempb[i] != null) {
                temp = new Node(null, null, tempb[i]);
                b[idx] = temp;
                //System.out.println(b[idx]);
                idx++;
            }
        }

        root = new Node(null, null, null);
        Stack<Node> stack = new Stack<Node>();
        for (int i = 0; i < b.length; i++) {
            if (b[i].getValue().equals("")"") != true) {
                stack.push(b[i]);
            } else {
                Node rightvalue = stack.pop();
                Node operator = stack.pop();
                Node leftvalue = stack.pop();
                operator.setLeft(leftvalue);
                operator.setRight(rightvalue);
                stack.pop();  //remove the nearest '('}
                stack.push(operator);

            }
        }
        root=stack.pop();
        return root;
    }

    private void Prefixtraversal(Node x, Queue<Node> q) {
        if (x == null) {
            return;
        }
        q.enqueue(x);
        Prefixtraversal(x.getLeft(), q);
        Prefixtraversal(x.getRight(), q);
    }

    public Node[] PrintPrefix() {
        if (this.root == null) {
            throw new NullPointerException();
        }
        Queue<Node> q = new Queue<Node>();
        Prefixtraversal(this.root, q);

        Node[] prefix = new Node[(b.length + 2) / 2];
        for (int i = 0; i < prefix.length; i++) {
            prefix[i] = q.dequeue();
            // System.out.println(prefix[i].getValue());
        }
        return prefix;
    }

    private void Postfixtraversal(Node x, Queue<Node> q) {
        if (x == null) {
            return;
        }
        Postfixtraversal(x.getLeft(), q);
        Postfixtraversal(x.getRight(), q);
        q.enqueue(x);
    }

    public Node[] PrintPostfix() {
        if (this.root == null) {
            throw new NullPointerException();
        }
        Queue<Node> q = new Queue<Node>();
        Postfixtraversal(this.root, q);
        Node[] postfix = new Node[(b.length + 2) / 2];
        for (int i = 0; i < postfix.length; i++) {
            postfix[i] = q.dequeue();
        }
        return postfix;
    }

    public double Evaluation() {
        if (this.root == null) {
            throw new NullPointerException();
        }
        Node[] c = this.PrintPostfix();
        Stack<Double> stack = new Stack<Double>();
        for (int i = 0; i < c.length; i++) {
            if (c[i].getValue().equals(""+"") == false && c[i].getValue().equals(""-"") == false && c[i].getValue().equals(""*"") == false && c[i].getValue().equals(""/"") == false) {
                stack.push(Double.valueOf(c[i].getValue()));
            } else {
                double num2 = 0.0;
                double num1 = 0.0;
                switch (c[i].getValue()) {
                    case ""+"":
                        num2 = stack.pop();
                        num1 = stack.pop();
                        stack.push(num1 + num2);
                        break;
                    case ""-"":
                        num2 = stack.pop();
                        num1 = stack.pop();
                        stack.push(num1 - num2);
                        break;
                    case ""*"":
                        num2 = stack.pop();
                        num1 = stack.pop();
                        stack.push(num1 * num2);
                        break;
                    case ""/"":
                        num2 = stack.pop();
                        num1 = stack.pop();
                        stack.push(num1 / num2);
                        break;
                }
            }

        }
        double answer = stack.pop();
        return answer;
    }

    public static void main(String[] args) {
        Expression b = new Expression();
  //      Node a = b.Infix2BT(""(((2+3)+(4+5))+((2+3)+(4+5)))"");
       //        Node a = b.Infix2BT(""(12+12)"");
        //   Node a = b.Infix2BT(""((1+1)+(1+1))"");
//        Node[] c = b.PrintPrefix();
//        for (int i = 0; i < c.length; i++) {
//            System.out.print(c[i].getValue());
//        }
//        System.out.println("" "");
//               c = b.PrintPostfix();
//        for (int i = 0; i < c.length; i++) {
//            System.out.print(c[i].getValue());
//        }
//        b.Evaluation();
//        System.out.println("" "");
      //  System.out.println(b.Evaluation());

    }
}

