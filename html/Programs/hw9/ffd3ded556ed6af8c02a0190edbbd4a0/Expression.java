import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Stack;

public class Expression {

    private Node root;

    // DO NOT MODIFY THIS
    public Expression() {
    }

    public int j = 1;
    public int com = 1;
    public String[] lala;

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

//    public static void main(String[] args) throws Exception {
//        try (BufferedReader br = new BufferedReader(new FileReader(""input.txt""))) {
//            String fund = br.readLine();
//            Expression exp = new Expression();
//            exp.Infix2BT(fund);
//            Node[] f=exp.PrintPrefix();
//            Node[] postfix = exp.PrintPostfix();
//            exp.PrintPostfix();
//            double h=exp.Evaluation();
//            System.out.println(h);
//        }
//        // TODO code application logic here
//    }

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
        if (h == 3) {
            root = new Node(root, root, lala[1]);
            return root;
        }
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
            if (x.getValue() != null) {
                la[j]=x;
                j++;
                i=j;
            }
            if(x.getLeft()!= null){
        pre(x.getLeft());}
            if(x.getRight()!= null){
        pre(x.getRight());return;}else{return;}
        }
    }
        public void post(Node x) {
        for(int i=0;i<tim;i++){
            if (x.getLeft() != null) {
                post(x.getLeft());
            }
            if (x.getRight() != null) {
                post(x.getRight());
            }
            if (x.getValue() != null) {
                la[j]=x;
                j++;
                i=j;
                return;
            }
        }
    }
    public Node[] PrintPrefix() {
        if(root==null)throw new NullPointerException();
        int l=0;
        j=0;
        la=new Node[tim];
        pre(root);
        Node[] prefix;
        prefix=la;
        return prefix;
    }

    public Node[] PrintPostfix() {
        if(root==null)throw new NullPointerException();
        int l=0;
        j=0;
        la=new Node[tim];
        post(root);
        Node[] postfix;
        postfix=la;
        return postfix;
    }

    public double Evaluation() {
        if(root==null)throw new NullPointerException();
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();
        int l = lala.length;
        int count = 0;
        if(l==3){return Double.parseDouble(lala[1]);}
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

