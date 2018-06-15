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
        Stack op = new Stack();
        //construct a stack
        for (int i = 0; i < howLong; i++) {

            token.push(trans[i]);
            token.push("" "");
            if ("")"".equals(trans[i]) && i > 2) {
                token.pop();
                op.push(token.pop());
                op.push("" "");
                op.push(token.pop());
                op.push("" "");
                op.push(token.pop());
                String[] a = op.toString().split("" "");
                token.push(subCal(a));
                token.push(trans[i]);

            }
            token.push("" "");

        }

        String[] preans = token.toString().split("" "");

        return Double.parseDouble(preans[1]);
    }

    double subCal(String[] a) {
        double ann = 0;
        if (a.length == 3) {
            if (""+"".equals(a[1])) {
                ann = Double.parseDouble(a[0]) + Double.parseDouble(a[2]);
            }
            if (""-"".equals(a[1])) {
                ann = Double.parseDouble(a[0]) - Double.parseDouble(a[2]);
            }
            if (""*"".equals(a[1])) {
                ann = Double.parseDouble(a[0]) * Double.parseDouble(a[2]);
            }
            if (""/"".equals(a[1])) {
                ann = Double.parseDouble(a[0]) / Double.parseDouble(a[2]);
            }

        }
        return ann;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

    }

}

