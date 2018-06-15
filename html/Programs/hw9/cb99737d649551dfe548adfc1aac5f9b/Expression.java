public class Expression{
  
    private Node root;

    // DO NOT MODIFY THIS
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
        Stack<Double> vals = new Stack<Double>();
        Stack<String> ops = new Stack<String>();
        String[] ee = infix.split("""");
        for (int i = 0; i < ee.length; i++) {
            if (ee[i].equals(""("")) {             
            } else if (ee[i].equals(""+"")) {
                ops.push(ee[i]);
            } else if (ee[i].equals(""-"")) {
                ops.push(ee[i]);
            } else if (ee[i].equals(""*"")) {
                ops.push(ee[i]);
            } else if (ee[i].equals(""/"")) {
                ops.push(ee[i]);
            } else if (ee[i].equals("")"")) {
                String op = ops.pop();
                if (op.equals(""+"")) {
                    vals.push(vals.pop() + vals.pop());
                } else if (op.equals(""-"")) {
                    vals.push((-1 * vals.pop()) + vals.pop());
                } else if (op.equals(""*"")) {
                    vals.push(vals.pop() * vals.pop());
                } else if (op.equals(""/"")) {
                    vals.push((1 / vals.pop()) * vals.pop());
                }
                String root = op;
            } else {
                vals.push(Double.parseDouble(ee[i])); 
            }
        }
        return root;
    }

    public Node[] PrintPrefix(){
        Node[] prefix = null;
        
        return prefix;
    }
  
    public Node[] PrintPostfix(){
        Node[] postfix = null;
        
        return postfix;
    }

    public double Evaluation(String infix){
        double answer = 0;
        Stack<Double> vals = new Stack<Double>();
        Stack<String> ops = new Stack<String>();
        String[] ee = infix.split("""");
        for (int i = 0; i < ee.length; i++) {
            if (ee[i].equals(""("")) {             
            } else if (ee[i].equals(""+"")) {
                ops.push(ee[i]);
            } else if (ee[i].equals(""-"")) {
                ops.push(ee[i]);
            } else if (ee[i].equals(""*"")) {
                ops.push(ee[i]);
            } else if (ee[i].equals(""/"")) {
                ops.push(ee[i]);
            } else if (ee[i].equals("")"")) {
                String op = ops.pop();
                if (op.equals(""+"")) {
                    vals.push(vals.pop() + vals.pop());
                } else if (op.equals(""-"")) {
                    vals.push((-1 * vals.pop()) + vals.pop());
                } else if (op.equals(""*"")) {
                    vals.push(vals.pop() * vals.pop());
                } else if (op.equals(""/"")) {
                    vals.push((1 / vals.pop()) * vals.pop());
                }
            } else {
                vals.push(Double.parseDouble(ee[i])); 
            }
        }
        answer = (vals.pop());
        return answer;
    }
}

