/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author user
 */
public class Calculator {
   ///////////////////////////////////////////
    /////////////////////////////////////////
    public Double ans (String e) {
        //for test
        //String []s = e.split("""");
        String [] e2 = e.split("" "");
         //String [] data = data0.split("","");
        int size = e2.length;
        //Stack<String> stack = new Stack<String>();
        //String [] word1 = new String[size]; 
        //word1 = e.split("" "");
        //System.out.println(word1[4]);
        //System.out.println(size);
        //System.out.println(""1"");
        //Store all string and split the integer
        int sum = 0;
        for (int i = size-1; i >= 0; i--) {
           //System.out.printf(""i is %s"",e2[i]);
           if(e2[i].equals(""("")){
               //e2[i] = null;
               //int []innersum = new int [2];
               Double innersum = Double.parseDouble(e2[i+1]);
               //int innera = 0;
               //int innerb = 0;
               String operator;
               for(int j=i+1;j<size;j++){
                   if(e2[j].equals(""+"")||e2[j].equals(""-"")||e2[j].equals(""*"")||e2[j].equals(""/"")){
                       Double b = Double.parseDouble(e2[j+1]);
                       if(e2[j].equals(""+"")) innersum = innersum+b;
                       else if(e2[j].equals(""-"")) innersum = innersum-b;
                       else if(e2[j].equals(""/"")) innersum = innersum/b;
                       else if(e2[j].equals(""*"")) innersum = innersum*b;
                       e2[j] = "" "";
                       e2[j-1] = "" "";
                       e2[j+1] = "" "";
                   } 
                   else if(e2[j].equals("")"")){
                       e2[i] = Double.toString(innersum);
                       e2[j] = "" "";
                       break;
                   }
               }
           }
           //stack.push(e
           //String word = e.substring(i, i+1);
           /*if (!word.equals("" "")){
               if(word.equals(""("")||word.equals("")"")||word.equals(""+"")||word.equals(""-"")||word.equals(""*"")||word.equals(""/""))
               stack.push(word);
               else{
                    int num = Integer.parseInt(word);
                    stack.push(num);
               }
           }*/
        }
        //System.out.println(stack.peek());
        //read the stack
        for(int i = 0; i < size; i++){
            
        }
        //store the ans
       /*while (StdIn.isEmpty()){
            String s1 = StdIn.readString();
            System.out.println(s1);
            if (s.equals(""-"")) StdOut.print(stack.pop());
            else stack.push(s);
        }*/
        
        //stack.toString();
.
        Double ans = Double.parseDouble(e2[0]);
      return ans;
    }
      
      //object for string
      /*private String Strinput(){
      int stackSize = s.length(); 
      Stack theStack = new Stack (stackSize); 
      for (int i = 0; i < input.length(); i++) {
         char ch = input.charAt(i); 
         theStack.push(ch); 
      }
      output = """";
      while (!theStack.isEmpty()) {
         char ch = theStack.pop(); 
         output = output + ch; 
      }
      return output;
        
        return null;
    
    }*/
    
    
    
    
    
    
    
    
    /*public static void main(String[] args) throws Exception{
        Calculator cct = new Calculator();
        cct.ans(""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) ) "");
    }*/
}

