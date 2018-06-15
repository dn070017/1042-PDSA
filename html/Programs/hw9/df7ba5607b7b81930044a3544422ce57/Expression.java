
public class Expression {

    private Node root;

    // DO NOT MODIFY THIS
    public Expression() {
    }

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix) {

        String a = infix.replace(""+"", "" + "");
        String b = a.replace(""-"", "" - "");
        String c = b.replace(""*"", "" * "");
        String d = c.replace(""/"", "" / "");
        String e = d.replace(""("", "" ( "");
        String f = e.replace("")"", "" ) "");
        String g = f.replace(""  "", "" "");
        String h = g.replaceFirst("" "", """");

        String[] stringarray = h.split("" "");

        Stack<Node> data = new Stack<Node>();
        for (int i = 0; i < stringarray.length; i++) {
            if (stringarray[i].equals("")"")) {
                Node[] temp = new Node[4];
                int tempi = 0;
                while (tempi < 4) {
                    temp[tempi] = data.pop();
                    tempi++;
                }
                temp[1].setLeft(temp[2]);
                temp[1].setRight(temp[0]);
                root = temp[1];
                data.push(root);
            } else {
                Node temp = new Node(null, null, stringarray[i]);
                data.push(temp);
            }
        }
        return root;
    }

    public Node[] PrintPrefix() {
        Queue<Node> data = new Queue<Node>();
        Node x = root;
        PrintPrefix(data, x);
        int i = 0, num = data.size();
        Node[] prefix = new Node[num];
        while (!data.isEmpty()) {
            prefix[i] = data.dequeue();
            i++;
        }
        return prefix;
    }

    private void PrintPrefix(Queue<Node> data, Node root) {
        Node x = root, L = root.getLeft(), R = root.getRight();
        data.enqueue(x);

        if (L != null) {
            PrintPrefix(data, L);
        }
        if (R != null) {
            PrintPrefix(data, R);
        }
    }

    public Node[] PrintPostfix() {
        Node x = root;
        Queue<Node> postdata = new Queue<Node>();
        PrintPostfix(postdata, x);
        int i = 0, num = postdata.size();
        Node[] postfix = new Node[num];
        while (!postdata.isEmpty()) {
            postfix[i] = postdata.dequeue();
            i++;
        }
        return postfix;
    }

    private void PrintPostfix(Queue<Node> postdata, Node root) {
        Node x = root, L = root.getLeft(), R = root.getRight();

        if (L != null) {
            PrintPostfix(postdata, L);
        }
        if (R != null) {
            PrintPostfix(postdata, R);
        }
        postdata.enqueue(x);
    }

    public double Evaluation() {
        double answer = 0;
        Node sign = root;
        answer = Evaluation(sign);
        return answer;
    }

    private double Evaluation(Node sign) {
        double ans = 0;
        Node x = sign;
        Node L = x.getLeft(), R = x.getRight();
        if (L.getValue().equals(""+"") || L.getValue().equals(""-"") || L.getValue().equals(""*"") || L.getValue().equals(""/"")) {
            L = new Node(null, null, String.valueOf(Evaluation(L)));
        }
        if (R.getValue().equals(""+"") || R.getValue().equals(""-"") || R.getValue().equals(""*"") || R.getValue().equals(""/"")) {
            R = new Node(null, null, String.valueOf(Evaluation(R)));
        }
        if (x.getValue().equals(""+"")) {
            double l = Double.parseDouble(L.getValue()), r = Double.parseDouble(R.getValue());
            ans = l + r;
        } else if (x.getValue().equals(""-"")) {
            double l = Double.parseDouble(L.getValue()), r = Double.parseDouble(R.getValue());
            ans = l - r;
        } else if (x.getValue().equals(""*"")) {
            double l = Double.parseDouble(L.getValue()), r = Double.parseDouble(R.getValue());
            ans = l * r;
        } else if (x.getValue().equals(""/"")) {
            double l = Double.parseDouble(L.getValue()), r = Double.parseDouble(R.getValue());
            ans = l / r;
        }
        return ans;
    }

}

