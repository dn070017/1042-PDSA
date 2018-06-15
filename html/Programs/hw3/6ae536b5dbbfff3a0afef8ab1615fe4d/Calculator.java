/**
.
 */
public class Calculator {

    public Stack<Double> numberStack=new Stack<Double>();
    public Stack<String> operatorStack=new Stack<String>();

/*    public static void main(String[] args) {
        Calculator calculator=new Calculator();

        System.out.println(calculator.ans(""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) )""));

    }*/
    public Double ans (String e){
        String[] data= e.split("" "");
/*        for(int i=0;i<data.length;i++){
            System.out.println(data[i]);
        }*/
        Double ans=0d;
        for(int i=0;i<data.length;i++){
            if(data[i].equals(""+"")|data[i].equals(""-"")|data[i].equals(""*"")|data[i].equals(""/"")){
                //System.out.println(""add operator"");
                operatorStack.push(data[i]);
            }
            else if(data[i].equals("")"")){
                //System.out.println(""calculate"");
                String operator=operatorStack.pop();
                Double A=numberStack.pop();
                Double B=numberStack.pop();
                if(operator.equals(""+"")){
                    ans=B+A;
                }
                else if(operator.equals(""-"")){
                    ans=B-A;
                }
                else if(operator.equals(""*"")){
                    ans=B*A;
                }
                else{
                    ans=B/A;
                }
                //System.out.println(ans.toString());
                numberStack.push(ans);
            }else if(data[i].equals(""("")){
                //System.out.println(""do nothing"");
            }else{
                    //System.out.println(""add number"");
                    //System.out.println(data[i].toString());
                  numberStack.push(Double.parseDouble(data[i]));
            }
        }

        return ans;
    }
    public void showStack(){

        Stack.Node printop=operatorStack.first;
        while (printop!=null){
            System.out.println(printop.item);
            printop=printop.next;
        }
        Stack.Node print=numberStack.first;
        while (print!=null){
            System.out.println(print.item);
            print=print.next;
        }

    }

    class Stack<T>{

        Node<T> first;
        class Node<T>{
            T item;
            Node next;

            Node(T item,Node next){
                this.item=item;
                this.next=next;
            }
        }
        public boolean isEmpty(){
            return first==null;
        }
        public void push(T item){
            Node oldfirst=first;
            first=new Node<T>(item,oldfirst);
            first.next=oldfirst;
        }
        public T pop(){
            T item=first.item;
            first=first.next;
            return item;
        }


    }
}

