/*
.
 * To change this template file, choose Tools | Templates
.
 */

import java.util.*;
import static java.lang.System.out;

/**
 *
 * @author user
 */
public class Calculator {

     private static int priority(char c) {
          return c == '+' || c == '-' ? 1 : c == '*' || c == '/' ? 2 : 0;
     }

     private static String toPostfix(String infix, boolean isPost) {
          String expr = isPost
                  ? infix : new StringBuffer(infix).reverse().toString();
          LinkedList<Character> stack = new LinkedList<>();
          StringBuilder output = new StringBuilder();
          char toStack = isPost ? '(' : ')';
          char toOutput = isPost ? ')' : '(';
          for (char c : expr.toCharArray()) {
               if (c == toStack) {
                    stack.add(c);
               } else if (""+-*/"".indexOf(c) != -1) {
                    while (!stack.isEmpty()
                            && priority(stack.getLast()) >= priority(c)) {
                         output.append(stack.removeLast());
                    }
                    stack.add(c);
               } else if (c == toOutput) {
                    while (stack.getLast() != toStack) {
                         output.append(stack.removeLast());
                    }
                    stack.removeLast();
               } else {
                    output.append(c);
               }
          }

          while (!stack.isEmpty()) {
               output.append(stack.removeLast());
          }

          return isPost ? output.toString() : output.reverse().toString();
     }

     public static String toPostfix(String infix) {
          return toPostfix(infix, true);
     }

     public static String toPrefix(String infix) {
          return toPostfix(infix, false);
     }

     public  static Double  ans(String str)//將後置式進行運算
     {
          Stack s = new Stack();
          String[] data =new String[str.replace("" "","""").length()]; 
          
          int j=0;
          String pattern ="""";
          /////////////
          for(int i = 0; i<toPostfix(str).trim().split("" "").length;i++){
//               System.out.println(i+"" ""+data[i]);
               if(toPostfix(str).trim().split("" "")[i].equals(pattern)==false){
                    data[j]=toPostfix(str).trim().split("" "")[i];
//                    System.out.print(""check ""+data[j]+"" target ""+toPostfix(str).trim().split("" "")[i]);
//                    System.out.println("""");
                    j++;
               }
                    
          }
////////////////          
          String ans = """";
          for (int i = 0; i < data.length; i++)//讀出後置式的各個運算元運算子來做計算
          {
               if (data[i] == null) {
                    break;
               }
//是運算子時
               if (data[i].equals(""+"") || data[i].equals(""-"") || data[i].equals(""*"") || data[i].equals(""/"") || data[i].equals(""^"")) {
                    double b = Double.parseDouble(s.pop().toString());//提出前面兩個儲存的值計算
                    double a = Double.parseDouble(s.pop().toString());
                    
                    
                    if (data[i].equals(""+"")) {
                         s.push((a + b));
                    } else if (data[i].equals(""-"")) {
                         s.push((a - b));
                    } else if (data[i].equals(""*"")) {
                         s.push( (a * b));
                    } else if (data[i].equals(""/"")) {
                         s.push((double)(a / b));
                    } else if (data[i].equals(""^"")) {
                         s.push((double) (Math.pow(a, b)));
                    }
               } else if(data[i] !="" "")
               {
                    s.push(data[i]);
               }

          }
          return(Double.parseDouble(s.pop().toString()));
     }

     /**
      * @param args the command line arguments
      */
     public static void main(String[] args) {
          // TODO code application logic here
//          In in = new In(args[0]);
//          String line = in.readLine();
////
//          String infix = ""(a+b)*(c+d)"";
//          out.println(toPostfix(line));
//          System.out.println(ans(line));
//          
     }

}

