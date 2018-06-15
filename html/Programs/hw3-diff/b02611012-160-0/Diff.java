


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
            else if (s.equals(""-"")) {
                ops.push(s);
            }
            else if (s.equals(""*"")) {
                ops.push(s);
            }
            else if (s.equals(""/"")) {
                ops.push(s);
            }
            else if (s.equals("")"")) {
                String op = ops.pop();
                if (op.equals(""+"")) {
                    vals.push(vals.pop() + vals.pop());
                } 
                else if (op.equals(""-"")) {
                    Double a = vals.pop();
                    Double b = vals.pop();
                    vals.push(b - a);
                }
                else if (op.equals(""*"")) {
                    vals.push(vals.pop() * vals.pop());
                }
                else if (op.equals(""/"")) {
                    Double a = vals.pop();
                    Double b = vals.pop();
                    vals.push(b / a);
                }
            }
            else vals.push(Double.parseDouble(s));
        }
        Double answer = vals.pop();
        StdOut.println(answer);
//            aa = Double.parseDouble(vals.pop());
        return answer;
    }

    /**
     *
     * @param args
     */
//    public static void main(String[] args) {
//        Calculator cct = new Calculator();
//        cct.ans(""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) )"");
//    }

}

