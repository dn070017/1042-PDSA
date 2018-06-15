
import java.io.FileReader;
import java.io.BufferedReader;

public class Calculator {

    Calculator() {

    }

    public Double ans(String e) {
.
        String[] input = e.split("" "");
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();
        
        for(int i=0;i<input.length;i++) {
            String s = input[i];
            
            if (s.equals(""("")) ; 
            else if (s.equals(""+"")) {
                ops.push(s);
            } else if (s.equals(""*"")) {
                ops.push(s);
            } else if (s.equals(""/"")){
                ops.push(s);
            }else if (s.equals(""-"")){
                ops.push(s);
            }else if (s.equals("")"")) {
                String op = ops.pop();
                if (op.equals(""+"")) {
                    vals.push(vals.pop() + vals.pop());
                } else if (op.equals(""*"")) {
                    vals.push(vals.pop() * vals.pop());
                } else if(op.equals(""/"")){
                    double k=vals.pop();
                    double m=vals.pop();
                    vals.push(m / k);
                } else if(op.equals(""-"")){
                    double k=vals.pop();
                    double m=vals.pop();
                    vals.push(m - k);
                }
            } else {
                vals.push(Double.parseDouble(s));
            }
        }
        //StdOut.println(vals.pop());
        double a = vals.pop();
        return a;
//        System.out.printf(""%d"", vals.pop());
//      double answer=0;
//        return input.length;
//      return answer;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String data = br.readLine();

            Calculator cct = new Calculator();
            double b = cct.ans(data);
            System.out.printf(""%f"", b);

        }
        // TODO code application logic here
    }

}

