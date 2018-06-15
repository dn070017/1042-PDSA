


/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author user
 */
public class Calculator {

    public Double ans(String e) {
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();
        String ss[] = e.split("" "");
        for (int i = 0; i < ss.length; i++) {
            String s = ss[i];
            if (s.equals(""("")) ; 
            else if (s.equals(""+"")) {
                ops.push(s);
            }
            else if (s.equals(""*"")) {
                ops.push(s);
            }
            else if (s.equals("")"")) {
                String op = ops.pop();
                if (op.equals(""+"")) {
                    vals.push(vals.pop() + vals.pop());
                } 
                else if (op.equals(""*"")) {
                    vals.push(vals.pop() * vals.pop());
                }
            }
            else vals.push(Double.parseDouble(s));
        }
        StdOut.println(vals.pop());
//            aa = Double.parseDouble(vals.pop());
        return vals.pop();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.printf(""-1"");
    }

}

