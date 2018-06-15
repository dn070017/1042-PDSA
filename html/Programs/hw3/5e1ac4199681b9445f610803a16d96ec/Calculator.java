
public class Calculator {

//    private String e;
//    public Calculator(String a) {
//        e = a;
//    }
    public Double ans(String e) {
.

        if (e == null) {
            return null;
        } else {

            Stack<String> ops = new Stack<String>();
            Stack<Double> vals = new Stack<Double>();

            String[] s = e.split("" "");
            for (int i = 0; i < s.length; i++) {
                if (s[i].equals(""("")) {

                } else if (s[i].equals(""+"")) {
                    ops.push(s[i]);
                } else if (s[i].equals(""*"")) {
                    ops.push(s[i]);
                } else if (s[i].equals(""-"")) {
                    ops.push(s[i]);
                } else if (s[i].equals(""/"")) {
                    ops.push(s[i]);
                } else if (s[i].equals("")"")) {
                    String op = ops.pop();
                    if (op.equals(""+"")) {
                        Double a = vals.pop() + vals.pop();
                        vals.push(a);
//                        System.out.printf(""%f\n"", a);
                    } else if (op.equals(""*"")) {
                        Double a = vals.pop() * vals.pop();
                        vals.push(a);
//                        System.out.printf(""%f\n"", a);
                    } else if (op.equals(""-"")) {
                        Double a = vals.pop() ;
                        Double b = vals.pop();
                        Double c = b - a;
                        vals.push(c);
//                        System.out.printf(""%f\n"", c);
                    } else if (op.equals(""/"")) {
                        Double a = vals.pop() ;
                        Double b = vals.pop();
                        Double c = b / a;
                        vals.push(c);
//                        System.out.printf(""%f\n"", c);
                    }
                } else {
                    vals.push(Double.parseDouble(s[i]));
                }
            }

            return vals.pop();
        }
    }

    public static void main(String[] args) {

        String loca0 = ""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) ) "";

        Calculator cct = new Calculator();
        System.out.printf(""%f"", cct.ans(loca0));

    }
}

