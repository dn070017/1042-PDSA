
public class Calculator {
    private Node first = null;
    
    private class Node{
        String item;
        Node next;}
    public Double ans(String e){
        String[] E;
        E =e.split("" "");
        double c = 0;
        for(int i = 0;i < E.length;i++){
            Node oldfirst = first;
                first = new Node();
                first.item = E[i];
                first.next = oldfirst;
            if (E[i].equals("")"")){
                double a = Double.parseDouble(first.next.next.next.item);
                double b = Double.parseDouble(first.next.item);
                //System.out.println(""a=""+a);
                //System.out.println(""b=""+b);
                if (first.next.next.item.equals(""+""))
                     c = a + b ;
                else if (first.next.next.item.equals(""-""))
                     c = a - b ;
                else if (first.next.next.item.equals(""*""))
                     c = a * b ;
                else if (first.next.next.item.equals(""/""))
                     c = a / b ;
                //System.out.println(""c=""+c);
                for(int j = 0;j < 5;j++)   
                    first = first.next;
                String C =String.valueOf(c);
                oldfirst = first;
                first = new Node();
                first.item = C;
                first.next = oldfirst;   
            }
        }
        return c;
    }
    
    
    
    public static void main(String[] args) {
        String answer =""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) )"" ;
        Calculator cct = new Calculator();
        System.out.println(cct.ans(answer));
        
}}

