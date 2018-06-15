
import java.util.*;

public class Expression{

    private Node root;

    // DO NOT MODIFY THIS
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
        String postfix = Infix2Postfix(infix);
        String[] operands = postfix.split("" "");

        java.util.Stack<Node> nodes = new java.util.Stack<> ();

        for (int i=1; i<operands.length; ++i) {
            String operand = operands[i];
            if (operand.charAt(0) >= '0' && operand.charAt(0) <= '9') {
                Node node = new Node(null, null, operand);
                nodes.push(node);
            }
            else {
                Node right = nodes.pop();
                Node left = nodes.pop();
                Node node = new Node(left, right, operand);
                nodes.push(node);
            }
        }

        root = null;
        if (!nodes.empty()) {
            root = nodes.pop();
        }
        return root;
    }

    public Node[] PrintPrefix(){
        if (root == null) {
            throw new NullPointerException();
        }

        return printPrefix(root);
    }

    private Node[] printPrefix(Node node) {
        // mid, left, right
        if (node == null) {
            return new Node[0];
        }

        Node[] leftNodes = printPrefix(node.getLeft());
        Node[] rightNodes = printPrefix(node.getRight());
        Node[] result = new Node[leftNodes.length + rightNodes.length + 1];
        result[0] = node;
        System.arraycopy(leftNodes, 0, result, 1, leftNodes.length);
        System.arraycopy(rightNodes, 0, result, leftNodes.length+1, rightNodes.length);

        return result;
    }

    public Node[] PrintPostfix(){
        if (root == null) {
            throw new NullPointerException();
        }

        return printPostfix(root);
    }

    private Node[] printPostfix(Node node) {
        // left, right, mid
        if (node == null) {
            return new Node[0];
        }

        Node[] leftNodes = printPostfix(node.getLeft());
        Node[] rightNodes = printPostfix(node.getRight());
        Node[] result = new Node[leftNodes.length + rightNodes.length + 1];
        System.arraycopy(leftNodes, 0, result, 0, leftNodes.length);
        System.arraycopy(rightNodes, 0, result, leftNodes.length, rightNodes.length);
        result[leftNodes.length + rightNodes.length] = node;

        return result;
    }

    public double Evaluation(){
        if (root == null) {
            throw new NullPointerException();
        }

        return evaluation(root);
    }

    private double evaluation(Node node) {
        String value = node.getValue();
        if (value.charAt(0) == '+' ||
            value.charAt(0) == '-' ||
            value.charAt(0) == '*' ||
            value.charAt(0) == '/') {
            double left = evaluation(node.getLeft());
            double right = evaluation(node.getRight());
            switch (value.charAt(0)) {
                case '+':
                    return left + right;
                case '-':
                    return left - right;
                case '*':
                    return left * right;
                case '/':
                    return left / right;
                default:
                    return 0;
            }
        }
        else {
            return Double.parseDouble(node.getValue());
        }
    }

    private String Infix2Postfix(String infix) {
        // infix to postfix
        java.util.Stack<Character> stack = new java.util.Stack<> ();
        String postfix = """";
        for (int i=0; i<infix.length(); ++i) {
            char c = infix.charAt(i);
            switch (c) {
                case '(':
                    stack.push(c);
                    break;

                case ')':
                    while (stack.peek() != '(') {
                        postfix += ' ';
                        postfix += stack.pop();
                    }
                    if (!stack.empty()) {
                        stack.pop();
                    }
                    break;

                case '+':
                case '-':
                case '*':
                case '/':
                    while (priority(stack.peek()) > priority(c)) {
                        postfix += ' ';
                        postfix += stack.pop();
                    }
                    stack.push(c);
                    break;

                default:
                    if (i==0 || !((infix.charAt(i-1)>='0' && infix.charAt(i-1)<='9') || infix.charAt(i-1)=='.')) {
                        postfix += ' ';
                    }
                    postfix += c;
                    break;
            }
        }

        return postfix;
    }

    private int priority (char operand) {
        switch(operand) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            default:
                return 0;
        }
    }
}

