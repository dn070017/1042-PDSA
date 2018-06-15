public class Expression {

    private Node root;
    private String[] b;

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
        b = new String[arraysize];
        for (int i = 0; i < tempb.length; i++) {
            if (tempb[i] != null) {
                b[idx] = tempb[i];
                //System.out.println(b[idx]);
                idx++;
            }
        }
        
        root = new Node(null, null, null);
        //若為(1+(1+1))的形式
        Stack<String> stack = new Stack<String>();
        if (b[2].equals(""+"") || b[2].equals(""-"") || b[2].equals(""*"") || b[2].equals(""/"")) {
            root.setValue(b[2]);
            Node temp = new Node(null, null, b[1]);
            root.setLeft(temp);
            int checknum = 0;
            Node connectnode = new Node(null, null, null);
            if ((b.length - 1 - 3) >= 3) {
                for (int i = 3; i < b.length; i++) {
                    if (checknum == 0) {
                        if (b[i].equals("")"") != true) {
                            stack.push(b[i]);
                        } else {

                            String rightvalue = stack.pop();
                            Node righttemp = new Node(null, null, rightvalue);
                            String operator = stack.pop();
                            String leftvalue = stack.pop();
                            Node lefttemp = new Node(null, null, leftvalue);
                            Node temproot = new Node(lefttemp, righttemp, operator);
                            connectnode = temproot;
                            stack.pop();  //remove the nearest '('}
                            checknum = 1;

                        }
                    } else {
                        if (b[i].equals("")"") != true) {
                            stack.push(b[i]);
                        } else {
                            String in = stack.pop();
                            if (in.equals(""+"") || in.equals(""-"") || in.equals(""*"") || in.equals(""/"")) {
                                String operator = in;
                                String leftvalue = stack.pop();
                                Node lefttemp = new Node(null, null, leftvalue);
                                Node temproot = new Node(lefttemp, connectnode, operator);
                                connectnode = temproot;
                                stack.pop();  //remove the nearest '('}
                            } else {
                                String rightvalue = in;
                                Node righttemp = new Node(null, null, rightvalue);
                                String operator = stack.pop();
                                Node temproot = new Node(connectnode, righttemp, operator);
                                connectnode = temproot;
                                stack.pop();  //remove the nearest '('}
                            }
                        }
                    }
                }
                root.setRight(connectnode);
            } else {
                temp = new Node(null, null, b[3]);
                root.setRight(temp);
            }
        } //若為((1+1)+1)的形式
        else if (b[b.length- 2].equals(""+"") || b[b.length - 2].equals(""-"") || b[b.length - 2].equals(""*"") || b[b.length - 2].equals(""/"")) {
            root.setValue(b[b.length - 2]);
            Node temp = new Node(null, null, b[b.length - 1]);
            root.setRight(temp);
            int checknum = 0;
            Node connectnode = new Node(null, null, null);
            for (int i = 0; i < b.length - 2; i++) {
                if (checknum == 0) {
                    if (b[i].equals("")"") != true) {
                        stack.push(b[i]);
                    } else {
                        String rightvalue = stack.pop();
                        Node righttemp = new Node(null, null, rightvalue);
                        String operator = stack.pop();
                        String leftvalue = stack.pop();
                        Node lefttemp = new Node(null, null, leftvalue);
                        Node temproot = new Node(lefttemp, righttemp, operator);
                        connectnode = temproot;
                        stack.pop();  //remove the nearest '('}
                        checknum = 1;
                    }
                } else {
                    if (b[i].equals("")"") != true) {
                        stack.push(b[i]);
                    } else {
                        String in = stack.pop();
                        if (in.equals(""+"") || in.equals(""-"") || in.equals(""*"") || in.equals(""/"")) {
                            String operator = in;
                            String leftvalue = stack.pop();
                            Node lefttemp = new Node(null, null, leftvalue);
                            Node temproot = new Node(lefttemp, connectnode, operator);
                            connectnode = temproot;
                            stack.pop();  //remove the nearest '('}
                        } else {
                            String rightvalue = in;
                            Node righttemp = new Node(null, null, rightvalue);
                            String operator = stack.pop();
                            Node temproot = new Node(connectnode, righttemp, operator);
                            connectnode = temproot;
                            stack.pop();  //remove the nearest '('}
                        }
                    }
                }
            }
            root.setLeft(connectnode);
        }//若為((1+1)+(1+1))的形式
        else {
            int rootposition = 0;
            for (int i = 0; i < b.length; i++) {
                if (b[i].equals("")"") || b[i + 2].equals(""("")) {
                    rootposition = i + 1;
                    break;
                }
            }
            root.setValue(b[rootposition]);
            int checknum = 0;
            Node connectnode = new Node(null, null, null);
            for (int i = 0; i < rootposition; i++) {  //先連接root左邊
                if (checknum == 0) {
                    if (b[i].equals("")"") != true) {
                        stack.push(b[i]);
                    } else {
                        String rightvalue = stack.pop();
                        Node righttemp = new Node(null, null, rightvalue);
                        String operator = stack.pop();
                        String leftvalue = stack.pop();
                        Node lefttemp = new Node(null, null, leftvalue);
                        Node temproot = new Node(lefttemp, righttemp, operator);
                        connectnode = temproot;
                        stack.pop();  //remove the nearest '('}
                        checknum = 1;
                    }
                } else {
                    if (b[i].equals("")"") != true) {
                        stack.push(b[i]);
                    } else {
                        String in = stack.pop();
                        if (in.equals(""+"") || in.equals(""-"") || in.equals(""*"") || in.equals(""/"")) {
                            String operator = in;
                            String leftvalue = stack.pop();
                            Node lefttemp = new Node(null, null, leftvalue);
                            Node temproot = new Node(lefttemp, connectnode, operator);
                            connectnode = temproot;
                            stack.pop();  //remove the nearest '('}
                        } else {
                            String rightvalue = in;
                            Node righttemp = new Node(null, null, rightvalue);
                            String operator = stack.pop();
                            Node temproot = new Node(connectnode, righttemp, operator);
                            connectnode = temproot;
                            stack.pop();  //remove the nearest '('}
                        }
                    }
                }
            }
            root.setLeft(connectnode);
            checknum = 0;
            for (int i = rootposition + 1; i < b.length; i++) {  //再連接root右邊
                if (checknum == 0) {
                    if (b[i].equals("")"") != true) {
                        stack.push(b[i]);
                    } else {
                        String rightvalue = stack.pop();
                        Node righttemp = new Node(null, null, rightvalue);
                        String operator = stack.pop();
                        String leftvalue = stack.pop();
                        Node lefttemp = new Node(null, null, leftvalue);
                        Node temproot = new Node(lefttemp, righttemp, operator);
                        connectnode = temproot;
                        stack.pop();  //remove the nearest '('}
                        checknum = 1;
                    }
                } else {
                    if (b[i].equals("")"") != true) {
                        stack.push(b[i]);
                    } else {
                        String in = stack.pop();
                        if (in.equals(""+"") || in.equals(""-"") || in.equals(""*"") || in.equals(""/"")) {
                            String operator = in;
                            String leftvalue = stack.pop();
                            Node lefttemp = new Node(null, null, leftvalue);
                            Node temproot = new Node(lefttemp, connectnode, operator);
                            connectnode = temproot;
                            stack.pop();  //remove the nearest '('}
                        } else {
                            String rightvalue = in;
                            Node righttemp = new Node(null, null, rightvalue);
                            String operator = stack.pop();
                            Node temproot = new Node(connectnode, righttemp, operator);
                            connectnode = temproot;
                            stack.pop();  //remove the nearest '('}
                        }
                    }
                }
            }
            root.setRight(connectnode);
        }

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
        System.out.println(c.length);
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
 //      Node a = b.Infix2BT(""((20120224791*(121259+797912))+(211544771212/2))"");
//        //       Node a = b.Infix2BT(""(12+12)"");
//                Node a = b.Infix2BT(""((1+1)+(1+1))"");
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
 //       System.out.println(b.Evaluation());

    }
}

