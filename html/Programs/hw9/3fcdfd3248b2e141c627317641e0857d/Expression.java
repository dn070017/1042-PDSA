
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Stack;

public class Expression {

    public class Node {

        private Node left;
        private Node right;
        private String value;

        public Node(Node left, Node right, String value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        public Node getLeft() {
            return (this.left);
        }

        public Node getRight() {
            return (this.right);
        }

        public String getValue() {
            return (this.value);
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    private Node root;

    // DO NOT MODIFY THIS
    public Expression() {
    }

    public int j = 1;
    public int com = 1;

    public void put(Node x) {
        for (int i = j; i < lala.length; i = i + 0) {
            String s;
            i = j;
            s = lala[i];
            Node left, right;
            switch (s) {
                case ""("":
                    Node y = null;
                    y = new Node(y, y, null);
                    j++;
                    put(y);
                    x.setLeft(y);
                    i = j;
                    continue;
                case ""+"":
                    x.setValue(""+"");
//                    System.out.println(""+"");
                    j++;
                    if (lala[j].equals(""("")) {
                        y = null;
                        y = new Node(y, y, null);
                        j++;
                        put(y);
                        x.setRight(y);
                        j++;
                    } else {
                        y = null;
                        y = new Node(y, y, lala[j]);
//                        System.out.println(lala[j]);
                        x.setRight(y);
                        j++;
                        i = j;
                        continue;
                    }
                    i = j;
                    continue;
                case ""*"":
                    x.setValue(""*"");
//                    System.out.println(""*"");
                    j++;
                    if (lala[j].equals(""("")) {
                        y = null;
                        y = new Node(y, y, null);
                        j++;
                        put(y);
                        x.setRight(y);
                        j++;
                    } else {
                        y = null;
                        y = new Node(y, y, lala[j]);
//                        System.out.println(lala[j]);
                        x.setRight(y);
                        j++;
                        i = j;
                        continue;
                    }
                    i = j;
                    continue;
                case ""-"":
                    x.setValue(""-"");
//                    System.out.println(""-"");
                    j++;
                    if (lala[j].equals(""("")) {
                        y = null;
                        y = new Node(y, y, null);
                        j++;
                        put(y);
                        x.setRight(y);
                        j++;
                    } else {
                        y = null;
                        y = new Node(y, y, lala[j]);
//                        System.out.println(lala[j]);
                        x.setRight(y);
                        j++;
                        i = j;
                        continue;
                    }
                    i = j;
                    continue;
                case ""/"":
                    x.setValue(""/"");
//                    System.out.println(""/"");
                    j++;
                    if (lala[j].equals(""("")) {
                        y = null;
                        y = new Node(y, y, null);
                        j++;
                        put(y);
                        x.setRight(y);
                        j++;
                    } else {
                        y = null;
                        y = new Node(y, y, lala[j]);
//                        System.out.println(lala[j]);
                        x.setRight(y);
                        j++;
                        i = j;
                        continue;
                    }
                    i = j;
                    continue;
                case "")"":
                    j++;
//                    lala[i]="")"";i = j;
                    return;
                default:
                    y = null;
                    y = new Node(y, y, lala[j]);
//                    System.out.println(lala[j]);
                    x.setLeft(y);
                    j++;
                    i = j;
                    continue;
            }
        }
    }
    String[] lala;

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(""input.txt""))) {
            String fund = br.readLine();
            Expression cct = new Expression();
            cct.Infix2BT(fund);
            Node[] f=cct.PrintPrefix();
            cct.PrintPostfix();
            double h=cct.Evaluation();
            System.out.println(h);
//            String iaia= ""1.1235"";
//            double k =Double .parseDouble(iaia);
//            System.out.println(k);
        }
        // TODO code application logic here
    }

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix) {
        char[] c_arr = infix.toCharArray();
        int m = c_arr.length;
        tim=m;
        int k=0;
        lala = new String[m];
        for (int i = 0; i < m; i++) {
            String s;
            s = Character.toString(c_arr[i]);
            switch (s) {
                case ""("":
                    lala[i-k] = ""("";
                    tim=tim-1;
                    break;
                case ""+"":
                    lala[i-k] = ""+"";
                    break;
                case ""*"":
                    lala[i-k] = ""*"";
                    break;
                case ""-"":
                    lala[i-k] = ""-"";
                    break;
                case ""/"":
                    lala[i-k] = ""/"";
                    break;
                case "")"":
                    lala[i-k] = "")"";
                    tim=tim-1;
                    break;
                default:
                    if (lala[i - k] != null) {
                        lala[i - k] = lala[i - k] + s;
                    } else {
                        lala[i - k] = s;
                    }
                    if (c_arr[i+ 1] == '+' || c_arr[i  + 1] == ')' || c_arr[i  + 1] == '/' || c_arr[i +1]=='*'||c_arr[i+1]=='-'){break;}else{
                    k++;tim--;
                    }
            }
        }
        int h=lala.length-k;
        String[] tep=new String[lala.length-k];
        System.arraycopy(lala, 0, tep, 0, h);
        lala=tep;
        Node a, b;
        String c = ""0"";
        root = new Node(root, root, c);
        put(root);
//        root.setLeft();
        return root;
    }
    public int tim;
    public Node[] la;
    public void pre(Node x) {
        for(int i=0;i<tim;i++){
            if (x.value != null) {
                la[j]=x;
                j++;
                i=j;
            }
            if(x.left!= null){
        pre(x.left);}
            if(x.right!= null){
        pre(x.right);return;}else{return;}
        }
    }
        public void post(Node x) {
        for(int i=0;i<tim;i++){
            if (x.left != null) {
                post(x.left);
            }
            if (x.right != null) {
                post(x.right);
            }
            if (x.value != null) {
                la[j]=x;
                j++;
                i=j;
                return;
            }
        }
    }
    public Node[] PrintPrefix() {
        int l=0;
        j=0;
        la=new Node[tim];
        pre(root);
        Node[] prefix;
        prefix=la;
        return prefix;
    }

    public Node[] PrintPostfix() {
        int l=0;
        j=0;
        la=new Node[tim];
        post(root);
        Node[] postfix;
        postfix=la;
        return postfix;
    }

    public double Evaluation() {
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();
        int l = lala.length, cal = 0;
        int count = 0;
        while (count < l) {
            String s;
            s = lala[count];
            switch (s) {
                case ""("":
                    break;
                case ""+"":
                    ops.push(s);
                    break;
                case ""*"":
                    ops.push(s);
                    break;
                case ""-"":
                    ops.push(s);
                    break;
                case ""/"":
                    ops.push(s);
                    break;
                case "")"":
                    String op = ops.pop();
                    if (op.equals(""+"")) {
                        vals.push(vals.pop() + vals.pop());
                    } else if (op.equals(""*"")) {
                        vals.push(vals.pop() * vals.pop());
                    } else if (op.equals(""-"")) {
                        vals.push(-1 * (vals.pop() - vals.pop()));
                    } else if (op.equals(""/"")) {
                        vals.push((1 / vals.pop()) * vals.pop());
                    }
                    break;
                default:
                    vals.push(Double.parseDouble(s));
                    break;
            }
            count++;
        }
        return vals.pop();
    }
}

