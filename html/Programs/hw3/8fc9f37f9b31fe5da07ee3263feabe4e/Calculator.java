public class Calculator {

    /**
     *
     * @param p
     * @return
     */
    public Double ans(String p) {
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();

        String[] s = p.split("" "");
        for (int i = 0; i < s.length; i++) {
            if (s[i].equals(""("")) {
            } else if (s[i].equals(""+"")) {
                ops.push(s[i]);
            } else if (s[i].equals(""-"")) {
                ops.push(s[i]);
            } else if (s[i].equals(""*"")) {
                ops.push(s[i]);
            } else if (s[i].equals(""/"")) {
                ops.push(s[i]);
            } else if (s[i].equals("")"")) {
                String op = ops.pop();
                if (op.equals(""+"")) {
                    vals.push(vals.pop() + vals.pop());
                } else if (op.equals(""*"")) {
                    vals.push(vals.pop() * vals.pop());
                } else if (op.equals(""-"")) {
                    double k = vals.pop();
                    vals.push(vals.pop() - k);
                } else if (op.equals(""/"")) {
                    double k = vals.pop();
                    vals.push(vals.pop() / k);
                }
            } else {
                vals.push(Double.parseDouble(s[i]));
            }

        }
        p = String.valueOf(vals.pop());
        double nn;
        nn = Double.parseDouble(p);
        return nn;
    }

    public static void main(String[] args) {
        String mm = ""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) ) "";
        Calculator cct = new Calculator();
        double rooo;
        rooo = cct.ans(mm);

        System.out.print(rooo);
    }
}

//        public int find(int p) {
//        if (p < 0 || p >= id.length) throw new IndexOutOfBoundsException();
//        while (p != id[p]) {
//            id[p] = id[id[p]];    // path compression by halving
//            p = id[p];
//        }
//        return p;
//    }

