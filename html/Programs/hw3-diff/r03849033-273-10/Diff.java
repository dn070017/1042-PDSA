
public class Calculator {
    public String a1=""+"";
    public String a2=""-"";
    public String a3=""*"";
    public String a4=""/"";
    public String a5=""("";
    public String a6="")"";

private static class Stack
{
	String [] stack;
	public int index;
	Stack(int max)
	{
		index = -1; //無元素
		stack = new String[max];
	}
	public void push(String data)
	{
		stack[++index] = data;
	}
	public String pop()
	{
		return stack[index--];
	}

        
}    
 
    public Double ans (String e) {
        String[] data = e.split("" "");
   	Stack s = new Stack(data.length);
        int count = 0;
        for(int i=0;i<data.length;i++){
            if(data[i].equals(a1)||data[i].equals(a2)||data[i].equals(a3)||data[i].equals(a4)){
                s.push(data[i]);
                count=1;    }   
            else if(data[i].equals(a5)) count=0;
            else if(data[i].equals(a6)){ count=0;
            if(s.index>1){
               Double b1 = Double.parseDouble(s.pop());
               String b2 = s.pop();
               Double b3 = Double.parseDouble(s.pop());
               if(b2.equals(a1)) s.push(""""+(b3+b1));
               if(b2.equals(a2)) s.push(""""+(b3-b1));
               if(b2.equals(a3)) s.push(""""+(b3*b1));
               if(b2.equals(a4)) s.push(""""+(b3/b1));
            }   } 
            else{
                s.push(data[i]);               
                if(count==1){
                count=0;    
               Double b1 = Double.parseDouble(s.pop());
               String b2 = s.pop();
               Double b3 = Double.parseDouble(s.pop());
               if(b2.equals(a1)) s.push(""""+(b3+b1));
               if(b2.equals(a2)) s.push(""""+(b3-b1));
               if(b2.equals(a3)) s.push(""""+(b3*b1));
               if(b2.equals(a4)) s.push(""""+(b3/b1));
                }
            }
        }
	return Double.parseDouble(s.pop());
}
    public static void main(String[] args) throws Exception {
        String a = ""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) )"";
        Calculator cct = new Calculator();
        System.out.println(cct.ans(a));
    }
}

