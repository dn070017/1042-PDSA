/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author Arthur
 */
import java.io.BufferedReader;
import java.io.FileReader;

public class Calculator {

    private String[] data;
    public Stack<String> op;

    Calculator() {
        //setstring(a);

    }

    double ans(String e) {
        String[] input = e.split("" "");
        Stack<String> operator = new Stack<String>();
        Stack<Double> value = new Stack<Double>();
        for (int i = 0; i < input.length; i++) {
            String s = input[i];
            if (s.equals(""("")) ; else if (s.equals(""+"")) {
                operator.push(s);
            } else if (s.equals(""*"")) {
                operator.push(s);
            } else if (s.equals(""/"")) {
                operator.push(s);
            } else if (s.equals(""-"")) {
                operator.push(s);
            } else if (s.equals("")"")) {
                String op = operator.pop();
                if (op.equals(""+"")) {
                    value.push(value.pop() + value.pop());
                } else if (op.equals(""*"")) {
                    value.push(value.pop() * value.pop());
                } else if (op.equals(""/"")) {
                    double k = value.pop();
                    double m = value.pop();
                    value.push(m / k);
                } else if (op.equals(""-"")) {
                    double k = value.pop();
                    double m = value.pop();
                    value.push(m - k);
                }
            } else {
                value.push(Double.parseDouble(s));
            }

//            String s=data[i];
//        if(!data[i].equals(""+"") && !data[i].equals(""-"") && !data[i].equals(""*"") && !data[i].equals(""/"") && !data[i].equals(""("") && !data[i].equals("")"") )
//        value.push(Double.parseDouble(s));
//        if(data[i].equals(""+""))
//            operator.push(s);
//        if(data[i].equals(""-""))
//            operator.push(s);
//        if(data[i].equals(""*""))
//            operator.push(s);
//        if(data[i].equals(""/""))
//            operator.push(s);
//        if(data[i].equals("")""))
//        {
//            String op= operator.pop();
//            if(op.equals(""+""))
//                value.push(value.pop()+value.pop());
//            else if(op.equals(""*""))
//                value.push(value.pop()*value.pop());
//            else if(op.equals(""-""))
//                value.push(value.pop()-value.pop());
//            else if(op.equals(""/""))
//                value.push(value.pop()/value.pop());
//        }
        }
        return value.pop();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String datadata;
            datadata = br.readLine();
            Calculator cct = new Calculator();
            //cct.printeq();
            double a = cct.ans(datadata);
            System.out.printf(""%f"", a);

        } catch (Exception e) {
//    System.err.println(""-1"");
            System.out.printf(""error"");
        }
        // TODO code application logic here
    }

}

