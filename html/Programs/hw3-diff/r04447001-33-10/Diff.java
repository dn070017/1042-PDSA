/*import edu.princeton.cs.algs4.Stack;
        /*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author jerry
 */
public class Calculator {
    public Calculator(){
    }
    
    
    public class Stack<Item>{
        
    private Node<Item> first;     // top of stack
    private int N;  
    private class Node<Item> {
        private Item item;
        private Node<Item> next;
    }
    public Stack() {
        first = null;
        N = 0;
    }
    public boolean isEmpty() {
        return first == null;
    }
    public int size() {
        return N;
    }
    public void push(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        N++;
    }
    
    public Item pop() {
        Item item = first.item;        // save item to return
        first = first.next;            // delete first node
        N--;
        return item;                   // return the saved item
    }
    
    
    }
    public double ans(String e){
        String[] data = e.split(""\\s+"");
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();
        for(int i = 0; i < data.length;i++){
        String s = data[i];
        if(s.equals(""("")) ;
        else if(s.equals(""+"")) ops.push(s);
        else if(s.equals(""*"")) ops.push(s);
        else if(s.equals(""-"")) ops.push(s);
        else if(s.equals(""/"")) ops.push(s);
        else if(s.equals("")""))
        {
            String op = ops.pop();
            if(op.equals(""+"")) vals.push(vals.pop() + vals.pop());
            else if (op.equals(""*"")) vals.push(vals.pop() * vals.pop());
            else if (op.equals(""-"")){
                double second = vals.pop();
                double first = vals.pop();
                vals.push(first-second);
            }
             else if (op.equals(""/"")){
                double second = vals.pop();
                double first = vals.pop();
                vals.push(first/second);
            }           
        }
        else vals.push(Double.parseDouble(s));
        }
        return vals.pop();
    }
    /*public static void main(String[] args) {
        Calculator cct = new Calculator();
        String test = ""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) ) "";
        System.out.print(cct.ans(test));
    }*/
}


