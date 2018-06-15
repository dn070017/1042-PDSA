/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author hung-wei
 */
public class Calculator {
    
    private Stack<String> C ;
    
    public Double ans (String str) {
        
            String[] Line1 = str.split("" "");
            C = new Stack();
            for (int i=0; i<Line1.length; i++){
                if (Line1[i].equals(""(""))
                    continue;
                else if (Line1[i].equals("")"")){
                    Compute();
                }                    
                else C.push(Line1[i]);
            }
      return Double.parseDouble(C.pop());
    }
    
    
    public void Compute(){
        double num2 = Double.parseDouble(C.pop());
        String sign = C.pop();
        double num1 = Double.parseDouble(C.pop());
        if (sign.equals(""+""))
            C.push( String.valueOf(num1+num2) );
        else if (sign.equals(""-""))
            C.push( String.valueOf(num1-num2) );
        else if (sign.equals(""*""))
            C.push( String.valueOf(num1*num2) );
        else if (sign.equals(""/""))
            C.push( String.valueOf(num1/num2) );
    }
    
    public static void main(String[] args) {
        Calculator cct = new Calculator();
        String str = ""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) ) "";
        System.out.println(cct.ans(str));
    }
}

