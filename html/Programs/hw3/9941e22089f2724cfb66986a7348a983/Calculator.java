import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;

public class Calculator {
	private Double ans;
	private String[] equation;
	private Stack<Double> num;
	private Stack<String> operator;
	
	public Calculator(String eq){
		ans = 0.0;
		equation = eq.split("" "");
		num = new Stack<Double>();
		operator = new Stack<String>();
	}
	
	public void ans_print(){
		System.out.println(ans);
	}
	
	private void add_sub(){
		Double num_2 = num.pop();
		Double num_1 = num.pop();
		Double a = 0.0;
		//System.out.println(num_1);
		//System.out.println(num_2);
		switch(operator.pop()) {  
            case ""+"": 
				//System.out.printf(""%f + %f = %f\n"",num_1,num_2,num_1 + num_2);
				num.push(num_1 + num_2);
                break; 
            case ""-"": 
				//System.out.printf(""%f - %f = %f\n"",num_1,num_2,num_1 - num_2);
				num.push(num_1 - num_2); 
                break; 
            default: 
                System.out.println(""Error""); 
        }
	}
	
	private void mul_div(){
		Double num_2 = num.pop();
		Double num_1 = num.pop();
		Double a = 0.0;
		//System.out.println(num_1);
		//System.out.println(num_2);
		switch(operator.pop()) {  
            case ""*"": 
				//System.out.printf(""%f x %f = %f\n"",num_1,num_2,num_1 * num_2);
				num.push(num_1 * num_2);
                break; 
            case ""/"": 
                //System.out.printf(""%f / %f = %f\n"",num_1,num_2,num_1 / num_2);
				num.push(num_1 / num_2); 
                break; 
            default: 
                System.out.println(""Error""); 
        }
	}
	
	private void fin(){
		while(!operator.isEmpty()){
			ea();
		}
		ans = num.pop();
	}
	
	private void arrange(){
		for(String s : equation){
			//System.out.println(s);
			if(s.matches(""[0-9]+"") || s.matches(""[0-9]+.[0-9]+"")){
				Double n;
				try{
					n = Double.parseDouble(s);
				}catch(Exception ex){
					n = new Double(Integer.parseInt(s));
				}
				
				num.push(n);
				if(!operator.isEmpty()){
					String o = operator.peek();
					if(o.matches(""[*/]"")) mul_div();
				}
			}else if(s.matches(""[*+-/]"")){
				operator.push(s);
			}else if(s.equals("")"")){
				while(!operator.peek().equals(""e"")){
					ea();
				}
				operator.pop();
			}else if(s.equals(""("")){
				operator.push(""e"");
			}else{
				System.out.println(""Uknown input : "" + s +""."");
			} 
		}
		fin();
	}
	
	// Elementary arithmetic
	private void ea(){
		String o = operator.peek();
		if(o.matches(""[*/]"")) mul_div();
		if(o.matches(""[+-]"")) add_sub();
	}
	
	public Double ans (String e) {
		Calculator Cal = new Calculator(e);
		Cal.arrange();
		return ans;
	}
	
	public static void main(String[] args) throws Exception {
		// read equation
		String eq = new String();
		if(args.length == 0){
			Scanner scanner = new Scanner(System.in);
			System.out.printf(""Input equation :"");
			eq = scanner.nextLine();
		}else{
			eq = args[0];
		}
		Calculator Cal = new Calculator(eq);
		Cal.arrange();
		Cal.ans_print();
	}	
}
