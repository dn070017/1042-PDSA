public class Expression{

    private Node root;
    private Node[] binaryTree;

    // DO NOT MODIFY THIS
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
        int parenCount = 0, levelCount = 0 , levelMax = 0;
        Character a;
        int[] idTemp = new int[infix.length()];
        for (int i = 0; i < infix.length(); i++) {
            a = infix.charAt(i);
            String s = a.toString();
            if (s.equals(""("")) { levelCount++; parenCount++; idTemp[i] = -1;}
            else if (s.equals("")"")) { levelCount--; idTemp[i] = -1;}
            else if (s.equals(""+"") || s.equals(""-"") || s.equals(""*"") || s.equals(""/"")) idTemp[i] = levelCount-1;
            else idTemp[i] = levelCount;
            if (idTemp[i] > levelMax) levelMax = idTemp[i];
        }
        int[] id = new int[infix.length()-parenCount*2];
        String[] newInfix = new String[infix.length()-parenCount*2];
        int index = 0, rootIndex = 0;
        for (int i = 0; i < idTemp.length; i++) {
            if (idTemp[i] != -1) {
                a = infix.charAt(i);
                newInfix[index] = a.toString();
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
            Node n = new Node(null,null,newInfix[i]);
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
        Node[] prefix = null;
        return prefix;
    }

    public Node[] PrintPostfix(){
        Node[] postfix = null;
        return postfix;
    }

    public double Evaluation(){
        double answer = 0;
        return answer;
    }
}
