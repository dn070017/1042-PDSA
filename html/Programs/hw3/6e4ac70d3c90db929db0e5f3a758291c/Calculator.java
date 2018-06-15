import java.util.Stack;

public class Calculator {

    public static void main(String[] args){

        // nwe一個Calculator的instance
        Calculator cct = new Calculator();

        // main()呼叫ans()函示
//        System.out.printf(""\nAnswer:%f."",cct.ans(""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) )""));
        cct.ans(""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) )"");
    }

    public static Double ans(String e) {

        String[] bottom = e.split("" "");
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();
        vals.push(999.0);

        int i = 0;
        double num = -1.0;
        int length = bottom.length;

        while (i<length){

            switch (bottom[i]){
                case ""("":
                    break;
                case ""+"":
                    ops.push(bottom[i]);
                    break;
                case ""-"":
                    ops.push(bottom[i]);
                    break;
                case ""*"":
                    ops.push(bottom[i]);
                    break;
                case ""/"":
                    ops.push(bottom[i]);
                    break;
                case "")"":
                    String op = ops.pop();
                    if      (op.equals(""+"")) vals.push(vals.pop() + vals.pop());
                    else if (op.equals(""-"")) vals.push(-(vals.pop() - vals.pop()));
                    else if (op.equals(""*"")) vals.push(vals.pop() * vals.pop());
                    else if (op.equals(""/"")) vals.push((1/vals.pop()) * vals.pop());
                    break;
                default:
                    vals.push(Double.parseDouble(bottom[i]));
            }
//            System.out.printf(""\n[NOW DOING [%s]][NOW VALUE [%f]]"",bottom[i], vals.peek());
            i++;
        }
        return vals.pop();
    }
}

