import java.util.ArrayList;
import java.util.List;

public class Expression{

    private Node root;
    private Node[] binaryTree;
    private String[] infixArray;

    // DO NOT MODIFY THIS
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
        String[] splitInfix = infix.split("""");
        String newInfix = splitInfix[0] + "","";
        for (int i = 1; i < infix.length()-1; i++) {
            if (splitInfix[i].equals(""("")) {
                if (!newInfix.endsWith("","")) newInfix = newInfix + "","";
                newInfix = newInfix + splitInfix[i] + "","";
            } else if (splitInfix[i].equals("")"")) {
                if (!newInfix.endsWith("","")) newInfix = newInfix + "","";
                newInfix = newInfix + splitInfix[i] + "","";
            } else if (splitInfix[i].equals(""+"")) {
                if (!newInfix.endsWith("","")) newInfix = newInfix + "","";
                newInfix = newInfix + splitInfix[i] + "","";
            } else if (splitInfix[i].equals(""-"")) {
                if (!newInfix.endsWith("","")) newInfix = newInfix + "","";
                newInfix = newInfix + splitInfix[i] + "","";
            } else if (splitInfix[i].equals(""*"")) {
                if (!newInfix.endsWith("","")) newInfix = newInfix + "","";
                newInfix = newInfix + splitInfix[i] + "","";
            } else if (splitInfix[i].equals(""/"")) {
                if (!newInfix.endsWith("","")) newInfix = newInfix + "","";
                newInfix = newInfix + splitInfix[i] + "","";
            } else { newInfix = newInfix + splitInfix[i];}
        }
        newInfix = newInfix + "")"";
        infixArray = newInfix.split("","");

        int parenCount = 0, levelCount = 0 , levelMax = 0;
        int[] idTemp = new int[infixArray.length];
        for (int i = 0; i < infixArray.length; i++) {
            if (infixArray[i].equals(""("")) { levelCount++; parenCount++; idTemp[i] = -1;}
            else if (infixArray[i].equals("")"")) { levelCount--; idTemp[i] = -1;}
            else if (infixArray[i].equals(""+"") || infixArray[i].equals(""-"") || infixArray[i].equals(""*"") || infixArray[i].equals(""/"")) {
                idTemp[i] = levelCount-1;
            } else idTemp[i] = levelCount;
            if (idTemp[i] > levelMax) levelMax = idTemp[i];
        }
        int[] id = new int[infix.length()-parenCount*2];
        String[] newInfixArray = new String[infix.length()-parenCount*2];
        int index = 0, rootIndex = 0;
        for (int i = 0; i < idTemp.length; i++) {
            if (idTemp[i] != -1) {
                newInfixArray[index] = infixArray[i];
                id[index] = idTemp[i];
                if (id[index] == 0) rootIndex = index;
                index++;
            }
        }

        int[] treeIndex = new int[id.length];
        int[] levelIndexCount = new int[levelMax+1];
        for (int i = 0; i < id.length; i++) {
            levelIndexCount[id[i]] += 1;
            for (int k = id[i]+1; k < levelIndexCount.length; k++) levelIndexCount[k] = 0;
            int startIndex = 1;
            for (int j = 0; j < id[i]; j ++) {
                if (levelIndexCount[j] == 0) startIndex = startIndex*2;
                else startIndex = startIndex*2+1;
            }
            treeIndex[i] = startIndex;
        }
        binaryTree = new Node[treeIndex.length];
        for (int i = 0; i < treeIndex.length; i++) {
            Node n = new Node(null,null,newInfixArray[i]);
            binaryTree[i] = n;
        }

        for (int i = 0; i < treeIndex.length; i++) {
            for (int j = 0; j < treeIndex.length; j++) {
                if (treeIndex[j] == treeIndex[i]*2) binaryTree[i].setLeft(binaryTree[j]);
                if (treeIndex[j] == treeIndex[i]*2+1) binaryTree[i].setRight(binaryTree[j]);
            }
        }
        return binaryTree[rootIndex];
    }

    public Node[] PrintPrefix(){
        if(root == null) throw new NullPointerException();
        List<Node> list = new ArrayList<Node>();
        findPrintPrefix(list, root);
        return list.toArray(new Node[list.size()]);
    }

    private void findPrintPrefix(List<Node> list, Node root){
        if (root == null) return;
        list.add(root);
        findPrintPrefix(list, root.getLeft());
        findPrintPrefix(list, root.getRight());
    }

    public Node[] PrintPostfix(){
        if(root == null) throw new NullPointerException();
        List<Node> list = new ArrayList<Node>();
        findPrintPostfix(list, root);
        return list.toArray(new Node[list.size()]);
    }

    private void findPrintPostfix(List<Node> list, Node root) {
        if (root == null) return;
        findPrintPostfix(list, root.getLeft());
        findPrintPostfix(list, root.getRight());
        list.add(root);
    }

    public double Evaluation(){
        if(root == null) throw new NullPointerException();
        return Expression.calculator(infixArray);
    }

    public static Double calculator(String[] e) {
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();
        for (int i = 0; i < e.length; i++) {
            String s = e[i];
            if (s.equals(""("")) ;
            else if (s.equals(""+"")) ops.push(s);
            else if (s.equals(""-"")) ops.push(s);
            else if (s.equals(""*"")) ops.push(s);
            else if (s.equals(""/"")) ops.push(s);
            else if (s.equals("")""))
            {
                String op = ops.pop();
                if (op.equals(""+"")) {
                    vals.push(vals.pop() + vals.pop());
                } else if (op.equals(""-"")) {
                    vals.push(- vals.pop() + vals.pop());
                } else if (op.equals(""*"")) {
                    vals.push(vals.pop() * vals.pop());
                } else if (op.equals(""/"")) {
                    Double a = vals.pop();
                    Double b = vals.pop();
                    vals.push(b/a);
                }
            }
            else vals.push(Double.parseDouble(s));
        }
        return vals.pop();
    }

//    public static void main(String[] args) {
//
//        String infix = ""(4+(((4*2)/2)/3))"";
//        String infix1 = ""((4*3)+((((4*2)/2)/3)+((2+1)*3)))"";
//        String infix2 = ""((4.4+3)+((((4*2)/2)/3)+((2+1)*3)))"";
//        Expression e = new Expression();
//        Node n = e.Infix2BT(infix2);
//        System.out.println(e.Evaluation());
//    }
}
