import edu.princeton.cs.algs4.Stack;
import java.io.BufferedReader;
import java.io.FileReader;

public class Calculator {

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            StringBuilder everything = new StringBuilder();
            String line;
            everything.append(br.readLine());//first num
            while ((line = br.readLine()) != null) {
                everything.append("" "");
                everything.append(line);
            }
            String input_string = everything.toString();
            Calculator cct = new Calculator();
            double answer = cct.ans(input_string);
            System.out.printf(""ans = "" + answer + ""\n"");
        }
    }

    public Double ans(String e) {
        Stack<String> operator = new Stack<String>();
        Stack<Double> value = new Stack<Double>();
        String[] data = e.split("" "");
        double temp_1 = 0.0, temp_2 = 0.0;
        for (int i = 0; i < (data.length); i++) {
            if (data[i].equals(""("")); //Doing nothing!
            else if (data[i].equals(""+"")) {operator.push(data[i]);} 
            else if (data[i].equals(""-"")) {operator.push(data[i]);} 
            else if (data[i].equals(""*"")) {operator.push(data[i]);}
            else if (data[i].equals(""/"")) {operator.push(data[i]);} 
            else if (data[i].equals("")"")) {
                String op = operator.pop();  //pop the last operator
                if (op.equals(""+"")) {value.push(value.pop() + value.pop());} 
                else if (op.equals(""*"")) {value.push(value.pop() * value.pop());}
                else if (op.equals(""-"")) {
                    temp_1 = value.pop();
                    temp_2 = value.pop();
                    value.push(temp_2 - temp_1);
                } 
                else if (op.equals(""/"")) {
                    temp_1 = value.pop();
                    temp_2 = value.pop();
                    value.push(temp_2 / temp_1);
                }
            } else {value.push(Double.parseDouble(data[i]));}
        }
        return value.pop();
    }
}

