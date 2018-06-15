

/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author 許志鵬
 */
public class Calculator {

    public Calculator() {
    }

    public double ans(String quest) {
        String[] trans = quest.split("" "");
        int howLong = trans.length;
        Stack token = new Stack();
        String[] a = new String[3];
        //construct a stack
        for (int i = 0; i < howLong; i++) {
           // System.out.println(token.toString());
            token.push(trans[i]);

            if ("")"".equals(trans[i]) && i >= 4) {
                token.pop();
                a[0] = token.pop().toString();
                a[1] = token.pop().toString();
                a[2] = token.pop().toString();
                token.pop();
                token.push(subCal(a));

            }

        }

        return Double.parseDouble(token.toString());

    }

    private double subCal(String[] a) {
        double ann;
        ann = 0.0;

        if (null != a[1]) {
            switch (a[1]) {
                case ""+"":
                    ann = Double.parseDouble(a[2]) + Double.parseDouble(a[0]);
                    break;
                case ""-"":
                    ann = Double.parseDouble(a[2]) - Double.parseDouble(a[0]);
                    break;
                case ""*"":
                    ann = Double.parseDouble(a[2]) * Double.parseDouble(a[0]);
                    break;
                case ""/"":
                    ann = Double.parseDouble(a[2]) / Double.parseDouble(a[0]);
                    break;
            }
        }

        return ann;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Calculator cct = new Calculator();
        System.out.print(cct.ans(""( 20 + 456 ) ""));

    }

}

