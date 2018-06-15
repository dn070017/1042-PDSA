public class Calculator {
//----------------------------API from textbook-------------------------------//
    private Node first;
    private int[] a;
    private class Node
    {
    String item;
    Node next;
    }
    public boolean isEmpty()
    {return first==null;}
    public void push(String item)
    {
        Node oldfirst=first;
        first=new Node();
        first.item=item;
        first.next=oldfirst;
    }
    public String pop()
    {
        String item=first.item;
        first=first.next;
        return item;
    }
//--------------------------Self define function------------------------------//
    public Calculator(){
    first=null;
    }
    public String localcalculate (String[] data){
        double a=Double.parseDouble(data[2]);
        double b=Double.parseDouble(data[0]); 
        double tempans=0;
        if (data[1].equals(""+""))
            tempans=(a+b);
        else if (data[1].equals(""-""))
            tempans=(a-b);
        else if (data[1].equals(""*""))
            tempans=(a*b);
        else if (data[1].equals(""/""))
            tempans=(a/b);
        return (String.valueOf(tempans));
      }
    public double ans(String e) {
            String[] a=e.split("" "");
            for (int i=0 ; i<a.length ; i++){
                if (a[i].equals("")"")){
                    String[] tempdata=new String[4];
                    int tempi=0;
                    while (tempi<4){
                   tempdata[tempi]=pop();
                   tempi++;
                        }
                push(localcalculate(tempdata));   
                }
               else
                   push(a[i]); 
                }
            return(Double.parseDouble(pop()));
          }
    }
}
