
import java.util.*;

public class Expression {

          private Node root;

          // DO NOT MODIFY THIS
          public Expression() {
          }

          private boolean isCalculator(String c) {
                    if (c.equals(""+"") || c.equals(""-"") || c.equals(""*"") || c.equals(""/"")) {
                              return true;
                    } else if (java.lang.Character.isDigit(c.charAt(0))) {
                              return true;
                    }
                    return false;
          }

          private boolean isOperation(String c) {
                    if (c.equals(""+"") || c.equals(""-"") || c.equals(""*"") || c.equals(""/"")) {
                              return true;
                    } else {
                              return false;
                    }
          }

          private void Calculate(Stack<Double> stack, String operation) {
                    double sce = stack.pop();
                    double fir = stack.pop();

                    if (operation.equals(""+"")) {
                              stack.push(fir + sce);
                    } else if (operation.equals(""-"")) {
                              stack.push(fir - sce);
                    } else if (operation.equals(""*"")) {
                              stack.push(fir * sce);
                    } else if (operation.equals(""/"")) {
                              stack.push(fir / sce);
                    }

          }

          private void prefix(ArrayList list, Node node) {
                    list.add(node);
                    if (node.getLeft() != null) {
                              prefix(list, node.getLeft());
                    }
                    if (node.getRight() != null) {
                              prefix(list, node.getRight());
                    }
          }

          private void posfix(ArrayList list, Node node) {

                    if (node.getLeft() != null) {
                              posfix(list, node.getLeft());
                    }
                    if (node.getRight() != null) {
                              posfix(list, node.getRight());
                    }
                    list.add(node);
          }

// Build a Binary and Return the Root
          public Node Infix2BT(String infix) {
                    String[] charater = infix.split("""");
                    charater = Arrays.copyOfRange(charater, 1, charater.length);
                    Stack<Node> treeNode = new Stack<Node>();
                    for (int counter = 0; counter < charater.length; counter++) {
                              if (isCalculator(charater[counter])) {
                                        treeNode.push(new Node(null, null, charater[counter]));
                              } else if (charater[counter].equals("")"")) {
                                        Node right = treeNode.pop();
                                        Node mid = treeNode.pop();
                                        Node left = treeNode.pop();
                                        mid.setLeft(left);
                                        mid.setRight(right);
                                        treeNode.push(mid);
                              }
                              else continue;
                    }
                    this.root = treeNode.pop();
                    return this.root;
          }

          public Node[] PrintPrefix() {
                    if (this.root == null) {
                              throw new java.lang.NullPointerException();
                    } else {
                              ArrayList<Node> array = new ArrayList<Node>();
                              prefix(array, this.root);
                              Node[] prefix = new Node[array.size()];

                              for (int count = 0; count < array.size(); count++) {
                                        prefix[count] = array.get(count);
                              }
                              return prefix;
                    }
          }

          public Node[] PrintPostfix() {
                    if (this.root == null) {
                              throw new java.lang.NullPointerException();
                    } else {
                              ArrayList<Node> array = new ArrayList<Node>();
                              posfix(array, this.root);
                              Node[] posfix = new Node[array.size()];

                              for (int count = 0; count < array.size(); count++) {
                                        posfix[count] = array.get(count);
                              }
                              return posfix;
                    }
          }

          public double Evaluation() {
                    if (this.root == null) {
                              throw new java.lang.NullPointerException();
                    } else {
                              ArrayList<Node> array = new ArrayList<Node>();
                              posfix(array, this.root);
                              Stack<Double> storge = new Stack<Double>();
                              for (int count = 0; count < array.size(); count++) {
                                        if (isOperation(array.get(count).getValue())) {

                                                  Calculate(storge, array.get(count).getValue());
                                        } else {
                                                  storge.push(Double.parseDouble(array.get(count).getValue()));
                                        }
                              }

                              return storge.pop();
                    }
          }
}


