import java.util.Stack;
import java.util.Scanner;

/**
 *
 * @author Jayden
 */
public class Calculator {

    public static void main(String[] args) throws Exception {
//        String abc = ""( ( 1 + 2 ) * 3 )"";
//        double aa;
//        aa = ans(abc);        
//        System.out.println(aa);
    }

     public double ans(String e) {
        double y = 0;

        Stack<String> str = new Stack<String>();
        Stack<Double> dou = new Stack<Double>();

        String[] ch = {""+"",""-"",""*"",""/""};
        double i = 0, j = 0,k;
        double a = 0, b = 0;
        String c;

        Scanner sca = new Scanner(e);
        String label = sca.next();
        while (label.isEmpty() == false) {
            if (label.equals(""+"")||label.equals(""-"")||label.equals(""*"")||label.equals(""/"")) {
                str.push(label);
            } else if (label.equals(""("")) {
                i++;
            } else if (label.equals("")"")) {
                b = dou.pop();
                a = dou.pop();
                c = str.pop();
                if (c.equals(""+"")) {
                    y = a + b;
                } else if (c.equals(""-"")) {
                    y = a - b;
                } else if (c.equals(""*"")) {
                    y = a * b;
                } else if (c.equals(""/"")) {
                    y = a / b;
                }
                dou.push(y);
                j++;
                if(j==i){
                    break;
                }
            } else {
                k = Integer.valueOf(label);
                dou.push(k);
            }
            label = sca.next();
        }

        return y;
    }
}

