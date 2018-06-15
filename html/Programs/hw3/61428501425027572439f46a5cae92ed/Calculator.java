import java.io.FileReader;
import java.io.BufferedReader;

public class Calculator {
    public Double ans (String e) {
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();
        String[] s = e.split("" "");
        for(int i = 0; i < s.length; i++) {
        if (s[i].equals(""(""))                    ;
        else if (s[i].equals(""+"")) ops.push(s[i]);
        else if (s[i].equals(""-"")) ops.push(s[i]);
        else if (s[i].equals(""*"")) ops.push(s[i]);
        else if (s[i].equals(""/"")) ops.push(s[i]);
        else if (s[i].equals("")"")){
            String op = ops.pop();
            double v = vals.pop();
            if (op.equals(""+""))      v = vals.pop() + v;
            else if (op.equals(""-"")) v = vals.pop() - v;
            else if (op.equals(""*"")) v = vals.pop() * v;
            else if (op.equals(""/"")) v = vals.pop() / v;
            vals.push(v);
        }
        else vals.push(Double.parseDouble(s[i]));
        }
          return vals.pop();
    }    
//    public static void main(String[] args) throws Exception {
//        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
//            String data = br.readLine();
//            String[] s = data.split("" "");
//            for(int i = 0; i < s.length; i++) {
//                System.out.println(s[i]);
//            }
//            Calculator cct = new Calculator();
//            System.out.printf(""%.0f\n"",cct.ans(data));
//        }
//    }
}

