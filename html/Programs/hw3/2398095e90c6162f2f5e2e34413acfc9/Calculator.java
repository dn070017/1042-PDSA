


/**
 * 1042 PDSA
 * hw03_Calculator
 * @author Robert
 */
public class Calculator {  
    private Calculator(){}
    
    // ( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) )
    public Double ans (String e) {
        // store the data split by "" "" 
        String[] data;
        double output = 0;
        data = e.split("" "");
        // tmp = [] size 5
        String[] tmp = new String[5];
        Stack<String> s = new Stack<String>();
        for (int i=0; i<data.length; i++){
            s.push(data[i]);
            if ("")"".equals(s.peek())){
                for (int j=0; j<5; j++)
                    tmp[j] = s.pop();
 
                double num1 = Double.parseDouble(tmp[3]);
                double num2 = Double.parseDouble(tmp[1]);
                switch (tmp[2]){
                    case ""+"":
                        output = num1+num2;
                        break;
                    case ""-"":
                        output = num1-num2;
                        break;
                    case ""*"":
                        output = num1*num2;
                        break;
                    case ""/"":
                        output = num1/num2;
                        break;
                }
                s.push(String.valueOf(output));
            }
        }
    return output;
    }
    

    
    public static void main(String[] args) {
        // TODO code application logic here
        String N = ""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) )"";
        // StdOut.println(N);
        Calculator cct = new Calculator();
        double a = cct.ans(N);
        StdOut.println(""answer: ""+a);
        }
    
}

