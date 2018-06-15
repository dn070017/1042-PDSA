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

    /*public static void main(String[] args) {
        Calculator cal = new Calculator();
        double ans = cal.ans(""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) )"");
         System.out.println(ans);
    }*/

    public Double ans(String e) {
        String[] data = e.split("" "");
        String Str = ""[0-9\\.]+"";
        String op = """";

        double temp = 0.0;

        LinkedStack<Double> num = new LinkedStack<Double>();

        for (int i = 0; i < data.length; i++) {
            int count = 1;
            int comp1 = 0;
            int comp2 = 0;
            double[] f_s = new double[2];
            for (int j = i; j >= 0; j--) {
                if (data[i].equals("")"")) {
                    //data[i] = ""c"";
                    if (data[j].matches(""[0-9\\.]+"")) {
                        //System.out.println(data[j]);
                        f_s[count] = Double.parseDouble(data[j]);
                        comp1 = j;
                        //System.out.println(f_s[count]);
                        count--;
                        data[j] = ""c"";
                    }
                    if (data[j].matches(""[\\+\\-\\*\\/]"")) {
                        comp2 = j;
                        op = data[j];
                        data[j] = ""c"";
                        //System.out.println(comp2);
                    }
                    if (data[j].equals(""("")) {
                        //System.out.println(f_s[0] + "" "" + f_s[1]);
                        //System.out.println(comp1 + "" "" + comp2);
                        if(f_s[0] == 0.0 && f_s[1] == 0.0){
                            double a = num.pop();
                            double b = num.pop();
                            temp = count(b,op,a);
                            num.push(temp);
                        }
                        else if(f_s[0] == 0.0 && f_s[1] != 0.0){
                            if(comp1>comp2){
                                temp = count(num.pop(),op,f_s[1]);
                                num.push(temp);
                            }
                            else{
                                temp = count(f_s[1],op,num.pop());
                                num.push(temp);
                            }
                        }
                        else{
                            temp = count(f_s[0],op,f_s[1]);
                            num.push(temp);
                        }


                        data[j] = ""c"";
                        break;
                    }

                } else {
                    break;
                }
            }
        }
        return num.pop();
    }

    public Double count(double a, String b, double c) {
        double count = 0.0;
        if (b.equals(""+"")) {
            count = a + c;
        }
        if (b.equals(""-"")) {
            count = a - c;
        }
        if (b.equals(""*"")) {
            count = a * c;
        }
        if (b.equals(""/"")) {
            count = a / c;
        }
        return count;
    }

}

