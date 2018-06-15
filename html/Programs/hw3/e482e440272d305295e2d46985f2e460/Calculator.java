/*
.
 * To change this template file, choose Tools | Templates
.
 */


/**
 *
 * @author chinweihsu
 */
public class Calculator {
    
    public class stack {
        private item head;
        
        private class item {
            String value;
            item next;
        }
        
        stack() {
            head = null;
        }
        
        public void push(String v){
             item insertItem = new item();
             insertItem.value = v;
             insertItem.next = head;
             head = insertItem;
        }
        
        public String pop(){
            if(!isEmpty()){
                item popItem = head;
                head = popItem.next;
                return popItem.value;
            }else
                return ""stack is empty"";
        }
        
        public boolean isEmpty(){
            return head == null;
        }
        public void show(){
            item temp = head;
            while(temp!=null){
                System.out.printf(temp.value);
                temp = temp.next;
            }
            System.out.printf(""\n"");
        }
        
    }
    
    public double ans (String e){
        double ans =0;
        stack theStack = new stack();
        String[] eq = e.split("" "");
        //for(String i :eq)
            //System.out.printf(i);
        //System.out.print(""\n"");
        for(int i=0; i<eq.length; i++){
            if(! eq[i].equals("")"")){
                if(! eq[i].equals(""("")){
                    theStack.push(eq[i]);
                    //System.out.printf(""push %s in stack\n"",eq[i]);
                    //theStack.show();
                }
            }
            else {
                double a = Double.parseDouble(theStack.pop());
                //System.out.printf(""pop %f from stack\n"",a);
                String m = theStack.pop();
                //System.out.printf(""pop %s from stack\n"",m);
                double b = Double.parseDouble(theStack.pop());
                //System.out.printf(""pop %f from stack\n"",b);
                    switch (m) {
                        case ""+"":
                            theStack.push(String.valueOf(b+a));
                            //System.out.printf(""push %s in stack\n"",b+a);
                            //theStack.show();
                            continue;
                        case ""-"":
                            theStack.push(String.valueOf(b-a));
                            //System.out.printf(""push %s in stack\n"",b-a);
                            continue;
                        case ""*"":
                            theStack.push(String.valueOf(b*a));
                            //System.out.printf(""push %s in stack\n"",b*a);
                            continue;
                        case ""/"":
                            theStack.push(String.valueOf(b/a));
                            //System.out.printf(""push %s in stack\n"",b/a);
                            continue;
                    }
            }
        }
        return Double.parseDouble(theStack.pop());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
      Calculator a = new Calculator();
      System.out.print(a.ans(""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) ) ""));
              
    }
    
}

.
        String line;
        String target = """";

        while ((line = in.readLine()) != null) {
            target += line + "" "";
        }
        String[] numbersArray = target.split("" "");
        //System.out.println(numbersArray[3]);
        Stack<String> S1 = new Stack<String>();
        Stack<String> S2 = new Stack<String>();
        Stack<String> S3 = new Stack<String>();
        Stack<String> S4 = new Stack<String>();
        int lon = numbersArray.length;

        for (int i = 0; i < lon; i++) {

            if (""+"".equals(numbersArray[i]) || ""-"".equals(numbersArray[i]) || ""*"".equals(numbersArray[i]) || ""/"".equals(numbersArray[i])) {
                S1.push(numbersArray[i]);

            } else if (""("".equals(numbersArray[i])) {
                S1.push(numbersArray[i]);

            } else if ("")"".equals(numbersArray[i])) {
                while (S1.peek() != ""("") {
                    S2.push(S1.pop());
                    S1.pop();
                    break;
                }
            } else if (i == (lon - 1)) {
                while (!S1.empty()) {
                    S2.push(S1.pop());
                    break;
                }
            } else {
                S2.push(numbersArray[i]);
            }
        }

        System.out.println(S2);
        while (!S2.empty()) {   //逆續輸出
            S3.push(S2.pop());
        }
        System.out.println(S3);

        while (!S3.empty()) {   //處理後綴
            String pop = S3.pop();
            if (""+"".equals(pop)) {
                double n1 = Double.parseDouble(S4.pop());
                double n2 = Double.parseDouble(S4.pop());
                double n3 = n2 + n1;
                S4.push(String.valueOf(n3));
            } else if (""-"".equals(pop)) {
                double n1 = Double.parseDouble(S4.pop());
                double n2 = Double.parseDouble(S4.pop());
                double n3 = n2 - n1;
                S4.push(String.valueOf(n3));
            } else if (""*"".equals(pop)) {
                double n1 = Double.parseDouble(S4.pop());
                double n2 = Double.parseDouble(S4.pop());
                double n3 = n2 * n1;
                S4.push(String.valueOf(n3));
            } else if (""/"".equals(pop)) {
                double n1 = Double.parseDouble(S4.pop());
                double n2 = Double.parseDouble(S4.pop());
                double n3 = n2 / n1;
                S4.push(String.valueOf(n3));
            } else {
                S4.push(pop);
            }
        }
        
        double ans = Double.parseDouble(S4.pop()); 
        return ans;
