
public class Calculator {
    private Node first = null;
    private class Node{
        String item;
        Node next;}
    public boolean isEmpty() {
        return first == null ; }
    public void push( String item ){
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }
    public void pop(){
        first = first.next;}
    public Double ans(String e){
        String[] E;
        E =e.split("" "");
        double c = 0;
        for(int i = 0;i < E.length;i++){
            if (E[i].equals("")"")){
                double a = Integer.parseInt(E[i-3]);
                double b = Integer.parseInt(E[i-1]);
                
                if (E[i-2].equals(""+""))
                     c = a + b ;
                else if (E[i-2].equals(""-""))
                     c = a - b ;
                else if (E[i-2].equals(""*""))
                     c = a * b ;
                else if (E[i-2].equals(""/""))
                     c = a / b ;
                for(i = 0;i < 5;i++)
                    pop();
                String C =String.valueOf(c);
                push(C);
                 
            }
            else push(E[i]);
            
        }

         return c;
    }
    
    
    
    public static void main(String[] args) {
        
    }
    
}

