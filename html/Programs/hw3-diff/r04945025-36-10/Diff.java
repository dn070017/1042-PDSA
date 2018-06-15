import java.io.FileReader;
import java.io.BufferedReader;

public class Calculator {

    private Stack<Double> numStk = new Stack<Double>();
    private Stack<String> opStk = new Stack<String>();
    private double result;

    private void parseStr(String in){
        in = in.replaceAll(""\\("", """");
        String[] exp = in.split(""\\s+"");

        for(String op: exp){
            //System.out.printf(""%s$\n"", op);
            if(!op.equals(""+"") && !op.equals(""-"") && !op.equals(""*"") && !op.equals(""/"") && !op.equals(""("") && !op.equals("")"") && !op.equals("""")){ numStk.push(Double.parseDouble(op)); }
            else if(!op.equals("")"")){ opStk.push(op); }
            else if(op.equals("")"")){
                Double a = numStk.pop();
                Double b = numStk.pop();
                String c = null;
                if(!opStk.isEmpty()) c = opStk.pop();
                //System.out.printf(""%1.3f\t%s\t%1.3f\n"", a, c, b);
                numStk.push(Calculator.calc(a, b, c));
            }
        }
        while(numStk.size() > 1){
            Double a = numStk.pop();
            Double b = numStk.pop();
            String c = null;
            if(!opStk.isEmpty()) c = opStk.pop();
            //System.out.printf(""%1.3f\t%s\t%1.3f\n"", a, c, b);
            numStk.push(Calculator.calc(a, b, c));
        }
        //System.out.printf(""%d\n"", numStk.size());
        result = numStk.pop();
    }

    public double ans(String in){
        parseStr(in);
        return result;
    }

    public static double calc(Double a, Double b, String op){
        if(op.equals(""+"")) { return (a + b); }
        if(op.equals(""-"")) { return (b - a); }
        if(op.equals(""*"")) { return (a * b); }
        if(op.equals(""/"")) { return (b / a); }
        return 0;
    }

    public static void main(String[] args) throws Exception{
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            /*int num = Integer.parseInt(br.readLine());
            Calculator cct = new Calculator(num);

            for(String in = br.readLine(); in != null; in = br.readLine()){
                double ans = cct.ans(in);
                System.out.printf(""%.2f\n"", ans);
            }*/
        }
    }
}

