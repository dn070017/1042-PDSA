import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Stack;

public class Calculator {
	public boolean isOperator (String op){
		switch(op){
			case ""+"":
				//System.out.println(""+"");
				return true;
			case ""-"":
				//System.out.println(""-"");
				return true;
			case ""*"":
				//System.out.println(""*"");
				return true;
			case ""/"":
				//System.out.println(""/"");
				return true;
			case ""("":
				//System.out.println(""("");
				return true;
			case "")"":
				//System.out.println("")"");
				return true;
			default:
				return false;
		}
	}
	public Double ans (String e) {
		  Stack<String> stack_post = new Stack<String>();
		  String s = new String();
		  Stack<String> stack_string = new Stack<String>();
	      String[] data = e.split("" "");
	      String temp1 = """";
	      String temp2 = """";
	      String temp ="""";
	      double double1;
	      double double2;
	      double double_temp;
	      for(int i = 0; i<data.length; i++){
	    	  if(isOperator(data[i])){
	    		  if(data[i].equals("")"")){
	    			  while(!stack_string.isEmpty()){
	    				  	temp = stack_string.pop();
	    				  	//System.out.println(""temp:""+temp);
	    				  	if(temp.equals(""("")){
		    					break;
		    				}else{
		    					stack_post.push((temp));
		    					//s = s +"",""+ temp;
		    					if(temp.equals(""*"")){
		    						stack_post.pop();
		    						double1 = Double.parseDouble(stack_post.pop());
		    						double2 = Double.parseDouble(stack_post.pop());
		    						double_temp = double2*double1;
		    						stack_post.push(double_temp+"""");
		    					}if(temp.equals(""/"")){
		    						stack_post.pop();
		    						double1 = Double.parseDouble(stack_post.pop());
		    						double2 = Double.parseDouble(stack_post.pop());
		    						double_temp = double2/double1;
		    						stack_post.push(double_temp+"""");
		    					}if(temp.equals(""+"")){
		    						stack_post.pop();
		    						double1 = Double.parseDouble(stack_post.pop());
		    						double2 = Double.parseDouble(stack_post.pop());
		    						double_temp = double2+double1;
		    						stack_post.push(double_temp+"""");
		    					}if(temp.equals(""-"")){
		    						stack_post.pop();
		    						double1 = Double.parseDouble(stack_post.pop());
		    						double2 = Double.parseDouble(stack_post.pop());
		    						double_temp = double2-double1;
		    						stack_post.push(double_temp+"""");
		    					} 		    					
		    				}
	    			  }
	    		  }else{
	    			  stack_string.push(data[i]);
	    			  //s = s+ "",""+temp;
	    		  }
	    	  }else{
	    		  stack_post.push(data[i]);
	    		  //s = s + "",""+data[i];
	    	  }
	      }

//	      String[] post = s.split("","");
//
//	      for(int i=0; i<post.length;i++){
//	    	  
//	      }
	      return Double.parseDouble(stack_post.pop());
	}
	public static void main(String[] args) throws Exception{
        // read file from args[0] in Java 7 style
		Calculator cc = new Calculator();
		double answer;
		answer = cc.ans(""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) )"");
		//cc.ans(""( 1 + 2 ) * ( 3 + 4 ) "");
		System.out.println(answer);
	}
}

