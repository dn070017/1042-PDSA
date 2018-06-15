/*
.
 * To change this template file, choose Tools | Templates
.
 */
//remove ans static
public class Calculator {

    public Double ans(String e){
.
                String[] splited = e.split("" "");

        Double result = 0.00;
        Stack stack = new Stack();
        Double[] Num = new Double[2];

        for (int i = 0; i < splited.length; i++) {
            String s = splited[i];

            if (!s.equals("")"") && !s.equals(""("")) {
                stack.push(s);

            } else if (s.equals("")"")) {
                Num[0] = Double.parseDouble((String) stack.pop());
                if (stack.peek().equals(""*"")) {
                    stack.pop();
                    Num[1] = Double.parseDouble((String) stack.pop());
                    result = Num[1] * Num[0];
                    stack.push(String.valueOf(result));
                } else if (stack.peek().equals(""/"")) {
                    stack.pop();
                    Num[1] = Double.parseDouble((String) stack.pop());
                    result = Num[1] / Num[0];

                    stack.push(String.valueOf(result));
                } else if (stack.peek().equals(""+"")) {
                    stack.pop();
                    Num[1] = Double.parseDouble((String) stack.pop());
                    result = Num[1] + Num[0];
                    stack.push(String.valueOf(result));
                } else if (stack.peek().equals(""-"")) {
                    stack.pop();
                    Num[1] = Double.parseDouble((String) stack.pop());
                    result = Num[1] - Num[0];
                    stack.push(String.valueOf(result));
                }

            }
        }
        return result;
        }
        
        
        
    public static void main(String[] args) throws Exception {

        
    }

}

  

    
