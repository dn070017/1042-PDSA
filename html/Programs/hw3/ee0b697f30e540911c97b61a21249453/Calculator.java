//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.util.Arrays;


public class Calculator {
    
public Double ans (String e) {
    //Stack<Item> means you need to indicate the data type of Stack
    Stack<String> stack = new Stack();
    String[] words = e.split(""\\s+"");
    for (int i = 0; i < words.length; i++) {
      //System.out.println(i);
      String s = words[i];
      if (s.equals("")"")) {
        Double t1 = Double.parseDouble(stack.pop());
        //check the operater is logical or ()
        while(true){
          String o1=stack.pop();
          if (o1.equals(""("")){
           break;}
          double t2 = Double.parseDouble(stack.pop());
          if (o1.equals(""*"")){
            t1=t2*t1;}
          else if (o1.equals(""/"")){
            t1=t2/t1;}
          else if (o1.equals(""+"")){
            t1=t2+t1;}
          else{t1=t2-t1;}   
        }
        //System.out.println(t1);
        String total = String.valueOf(t1);
        stack.push(total);
      }
      else {stack.push(s);}
    }
      double calculated = Double.parseDouble(stack.pop());
      return calculated;
}
    
    public static void main(String[] args) throws Exception {
       Calculator cct = new Calculator();
       String a = ""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) )"";
       System.out.println(cct.ans(a));
        }
}
    

