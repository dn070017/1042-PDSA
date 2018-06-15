import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Stack;

public class Calculator {
    public Calculator(){
        
    }
    public Double ans(String e){
        String[] cut;
        cut=e.split("" "");
        String s;
        Stack<String> ops = new Stack<String>(); 
        Stack<Double> vals = new Stack<Double>();
        double help;
        int count;
        if(e.length()%2==0) count=e.length()/2;
        else count=(e.length()+1)/2;
        
        for(int i=0;i<count;i++){
            s=cut[i];
            if (s.equals(""("")) ; 
            else if (s.equals(""+"")) ops.push(s);
            else if (s.equals(""-"")) ops.push(s);
            else if (s.equals(""/"")) ops.push(s);
            else if (s.equals(""*"")) ops.push(s); 
            else if (s.equals("")"")){ 
                String op = ops.pop(); 
                if (op.equals(""+"")) vals.push(vals.pop() + vals.pop()); 
                else if (op.equals(""*"")) vals.push(vals.pop() * vals.pop());
                else if (op.equals(""-"")){
                    help = -(vals.pop() - vals.pop());
                    vals.push(help);
                }
                else if (op.equals(""/"")){
                     help = 1/(vals.pop() / vals.pop());
                    vals.push(help);
                }
            } 
            else { 
                vals.push(Double.parseDouble(s));
            } 
        }
        return vals.pop();
    }
  
     public static void main(String[] args) throws Exception {
         try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String str = br.readLine();
            Calculator cct = new Calculator();
            StdOut.println(cct.ans(str));
        }
    }
}

