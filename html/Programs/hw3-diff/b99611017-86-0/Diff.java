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
        //construct a stack
        for (int i = 0; i < howLong; i++) {

            if (i > 2 && i < howLong - 1) {

                if ("")"".equals(trans[i])) {

                    if (""+"".equals(trans[i - 2])) {

                        token.pop();
                        token.pop();
                        token.pop();
                        token.pop();
                        token.push(Double.parseDouble(trans[i - 1]) + Double.parseDouble(trans[i - 3]));
                        token.push(trans[i]);

                    }

                    if (""+"".equals(trans[i - 2])) {

                        token.pop();
                        token.pop();
                        token.pop();
                        token.pop();
                        token.push(Double.parseDouble(trans[i - 1]) + Double.parseDouble(trans[i - 3]));
                        token.push(trans[i]);

                    }

                    if (""-"".equals(trans[i - 2])) {

                        token.pop();
                        token.pop();
                        token.pop();
                        token.pop();
                        token.push(Double.parseDouble(trans[i - 3]) - Double.parseDouble(trans[i - 1]));
                        token.push(trans[i]);

                    }

                    if (""*"".equals(trans[i - 2])) {

                        token.pop();
                        token.pop();
                        token.pop();
                        token.pop();
                        token.push(Double.parseDouble(trans[i - 1]) * Double.parseDouble(trans[i - 3]));
                        token.push(trans[i]);

                    }

                    if (""/"".equals(trans[i - 2])) {

                        token.pop();
                        token.pop();
                        token.pop();
                        token.pop();
                        token.push(Double.parseDouble(trans[i - 3]) / Double.parseDouble(trans[i - 1]));
                        token.push(trans[i]);

                    }
                } else {
                    token.push(trans[i]);
                }
            } else {
                token.push(trans[i]);
            }
        }

        String[] preans = token.toString().split("" "");

        return Double.parseDouble(preans[1]);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

}

