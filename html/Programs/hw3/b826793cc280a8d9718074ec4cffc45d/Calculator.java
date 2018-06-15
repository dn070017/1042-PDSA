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
    public Double ans (String e) {
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
            }
            else
                push(equation[i]);
        }
        return Double.parseDouble(pop());
    }
}
