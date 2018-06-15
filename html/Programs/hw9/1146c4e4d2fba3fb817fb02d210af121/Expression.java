
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Jayden
 */
public class Expression {

    private Node root;
    List<Node> nodeList = new ArrayList<>();
    ;
    String infixClone;

    // DO NOT MODIFY THIS
    public Expression() {
    }

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix) {
        StringBuffer sb = new StringBuffer(infix);
        for (int i = 0; i < sb.length(); i++) {
            String kk = sb.substring(i, i + 1);
            if (sb.substring(i, i + 1).equals(""("") || sb.substring(i, i + 1).equals("")"") || sb.substring(i, i + 1).equals(""+"") || sb.substring(i, i + 1).equals(""-"") || sb.substring(i, i + 1).equals(""*"") || sb.substring(i, i + 1).equals(""/"")) {
                sb.insert(i, "" "");
                sb.insert(i + 2, "" "");
                i = i + 2;
            }
        }
        infixClone = sb.toString();//複製一份infix
        Stack<Node> st = new Stack<Node>();

        Scanner sca = new Scanner(infixClone);
        String value;
        Node node;
        Node a, operator, b;//等一下pop出來的算式是a+-*/b
        while (sca.hasNext()) {
            if (sca.hasNextDouble()) {//如果下一個是數字，就轉成node在記錄
                value = String.valueOf(sca.nextDouble());
                node = new Node(null, null, value);
                st.push(node);
            } else {
                value = sca.next();
                if (value.equals("")"")) {
                    b = st.pop();//第一個pop出來是後面的數字b
                    operator = st.pop();//第二個是運算式
                    a = st.pop();//第三個是前面的數字a
                    operator.setLeft(a);//運算式的左邊是前面的數字a
                    operator.setRight(b);//運算式的右邊是後面的數字b
                    st.push(operator);//回傳運算式

                } else if (value.equals(""("")) {//如果讀到的是左括號，就不做任何事情
                } else {
                    node = new Node(null, null, value);
                    st.push(node);
                }
            }
        }
        root = st.pop();
        return root;
    }

    public Node[] PrintPrefix() {
        nodeList.clear();//清除原本的nodeList
        prefixRecursive(root);//執行recursive
        Node[] prefix=new Node[nodeList.size()];
        nodeList.toArray(prefix);//將list轉成array
        return prefix;
    }

    public Node[] PrintPostfix() {
        nodeList.clear();//清除原本的nodeList
        postfixRecursive(root);//執行recursive
        Node[] postfix=new Node[nodeList.size()];
        nodeList.toArray(postfix);//將list轉成array
        return postfix;
    }

    public double Evaluation() {
        double answer = 0;
        Stack<String> str = new Stack<String>();
        Stack<Double> dou = new Stack<Double>();
        //兩個stack用來記錄兩種型態的資料
        double a, b;//ab是用來紀錄兩個pop出來的數字
        String c;//c則是用來記錄pop出來的運算符號

        Scanner sca = new Scanner(infixClone);
        String label;
        //scanner是讀取一個一個單字的用法，label則是存讀進來的是什麼東西
        while (sca.hasNext()) {
            if (sca.hasNextDouble()) {
                dou.push(sca.nextDouble());//如果下一個是數字，就記錄數字
            } else {//其餘的就是符號
                label = sca.next();
                if (label.equals(""+"") || label.equals(""-"") || label.equals(""*"") || label.equals(""/"")) {
                    str.push(label);
                } else if (label.equals("")"")) {
                    b = dou.pop();
                    a = dou.pop();
                    c = str.pop();
                    if (c.equals(""+"")) {
                        answer = a + b;
                    } else if (c.equals(""-"")) {
                        answer = a - b;
                    } else if (c.equals(""*"")) {
                        answer = a * b;
                    } else if (c.equals(""/"")) {
                        answer = a / b;
                    }
                    dou.push(answer);//再存入剛剛算出來的y
                }
            }
        }
        return answer;
    }

    private void prefixRecursive(Node n) {
        nodeList.add(n);//先儲存再往左往右
        if (n.getLeft() != null) {
            prefixRecursive(n.getLeft());
        }
        if (n.getRight() != null) {
            prefixRecursive(n.getRight());
        }
        return;
    }

    private void postfixRecursive(Node n) {
        if (n.getLeft() != null) {
            postfixRecursive(n.getLeft());
        }
        if (n.getRight() != null) {
            postfixRecursive(n.getRight());
        }
        nodeList.add(n);//先往左往右再儲存
        return;
    }
}

