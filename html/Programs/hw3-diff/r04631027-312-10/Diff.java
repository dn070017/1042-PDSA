/*
.
 * To change this template file, choose Tools | Templates
.
import java.util.Scanner;
import java.util.Stack;
/**
 *
 * @author YuChing
 */
public class Calculator {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        Calculator cct = new Calculator();
        Double ans1 = cct.ans(""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) )"");
        System.out.print(""ohya""+ans1);
    }
    public Double ans(String e)
    {   
        Double finalans =0.0;
       String[] begin = e.split("" "");
        Stack cc = new Stack();
        for (int i=0;i<begin.length;i++) {
            if (begin[i].equalsIgnoreCase("")""))
            {
                Double one = Double.parseDouble(cc.pop());
                String sign = cc.pop();
                Double two = Double.parseDouble(cc.pop());
                if (sign.equals(""+""))
                    cc.push((two+one)+"""");
                if(sign.equals(""-""))
                    cc.push((two-one)+"""");
                if(sign.equals(""*""))
                    cc.push((two*one)+"""");
                if(sign.equals(""/""))
                    cc.push((two/one)+"""");
            }
            else if(begin[i].equalsIgnoreCase(""("")) {
              //  System.out.println(i);
            } else {
                cc.push(begin[i]);
            }
        }
       
        Double thisismyanswer =Double.parseDouble(cc.pop());
//System.out.println(cc.pop());         
return thisismyanswer;
        
}
    public class Stack {

        private Node first = null;
        private class Node {
            String item;
            Node next;
        }
        public boolean isEmpty() {
            return first == null;
        }
        public void push(String item) {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
        }
        public String pop() {
            String item = first.item;
            first = first.next;
            return item;
        }
    }
}

