/*
.
 * To change this template file, choose Tools | Templates
.
 */
package calculator;

//import edu.princeton.cs.algs4.StdIn;
//import edu.princeton.cs.algs4.StdOut;
//import edu.princeton.cs.algs4.Stack;

/**
 *
 * @author Eric
 */
public class Calculator {
    public Calculator(){
    first = null;
    }
    private Node first;
    private int[] a;
    private class Node{
    String item;
    Node next;
    }
    
    public boolean isEmpty()
    {return first==null;}
    
    public void push(String item){
        Node oldfirst=first;
        first=new Node();
        first.item=item;
        first.next=oldfirst;
    }
    
    public String pop(){
        String item=first.item;
        first=first.next;
        return item;
    }
    
//    private static Object stack;
//    public static void main(String[] args) {
    public Double ans (String e) {
//        String e = ""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) )"";
        String[] equation = e.split("" "");  //Split string by space
//        Stack stk = new Stack();
        for (int i=0;i<equation.length;i++){
            if (equation[i].equals("")"")){
                String temp1 , temp2, temp3, temp4;
                double temp = 0;
                temp1 = (String) pop();
                temp2 = (String) pop();
                temp3 = (String) pop();
                temp4 = (String) pop();
                // do the operation
                if (temp2.equals(""+""))
                    temp = Double.parseDouble(temp3) + Double.parseDouble(temp1);
                else if (temp2.equals(""-""))
                    temp = Double.parseDouble(temp3) - Double.parseDouble(temp1);
                else if (temp2.equals(""*""))
                    temp = Double.parseDouble(temp3) * Double.parseDouble(temp1);
                else if (temp2.equals(""/""))
                    temp = Double.parseDouble(temp3) / Double.parseDouble(temp1);
                push(String.valueOf(temp));
//                System.out.println(pop());
            }
            else
                push(equation[i]);
            
        }
//        double an = 0;
//        an = Double.parseDouble(stk.pop());
        return Double.parseDouble(pop());
//        System.out.println(pop());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //String e = ""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) )"";
        
        //System.out.println(ans(e));
    }
    
}

