import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;

public class Calculator {
	private Double ans;
	private String[] equation;
	private Stack<Double> num;
	private Stack<String> operator;
	
	public Calculator(){
		ans = 0.0;
		//equation = eq.split("" "");
		num = new Stack<Double>();
		operator = new Stack<String>();
	}
	
	private void add(){
		operator.pop();
		if(num.size() > 1){
			Double num_1 = num.pop();
			Double num_2 = num.pop();
			//System.out.printf(""%f + %f = %f\n"",num_1,num_2,num_1 + num_2);
			num.push( num_1 + num_2 );
		}
	}
	
	private void mul(){
		operator.pop();
		//System.out.printf(""%f x %f = %f\n"",num_1,num_2,num_1 * num_2);
		if(num.size() > 1){
			Double num_1 = num.pop();
			Double num_2 = num.pop();
			//System.out.printf(""%f x %f = %f\n"",num_1,num_2,num_1 * num_2);
			num.push( num_1 * num_2 );
		}
	}
	
	private void fin(){
		// add all numbers
		while(!operator.isEmpty() && !num.isEmpty()){
			add();
		}
		ans = num.pop();
	}
	
	private void check(Double n){
		if(!operator.isEmpty()){
			String o = operator.peek();
			if(o.equals(""-"")){ // n -> -n, - -> +
				//System.out.println(""push -n "" + -n);
				num.push(-n);
				operator.pop();
				operator.push(""+"");
			}else if(o.equals(""/"")){ // n -> 1/n, / -> *
				//System.out.println(""push 1/n "" + 1/n);
				num.push(1.0/n);
				operator.pop();
				operator.push(""*"");
			}else{
				//System.out.println(""push "" + n);
				num.push(n);
			}
			o = operator.peek();
			while(o.equals(""*"")){
				mul();
				if(operator.isEmpty())break;
				o = operator.peek();
				//System.out.println(o);
			}
		}else{
			//System.out.println(""push "" + n);
			num.push(n);
		}
			
		/*System.out.printf(""Stack :"");
		for(String k : operator){
			System.out.printf(""%s "", k);
		}
		System.out.println();*/
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
				check(n);
			}else if(s.matches(""[*+-/]"")){
				//System.out.println(""push "" + s);
				operator.push(s);
			}else if(s.equals("")"")){
				while(!operator.peek().equals(""e"")){
					add();
				}
				operator.pop();
				check(num.pop());			
			}else if(s.equals(""("")){
				operator.push(""e"");
			}else{
				continue;
				//System.out.println(""Uknown input : "" + s +""."");
			} 
		}
		fin();
	}
	
	public Double ans (String e) {
		equation = e.split("" "");
		arrange();
		return ans;
	}
	
	public static void main(String[] args){
		// read equation
		String eq = new String();
		if(args.length == 0){
			Scanner scanner = new Scanner(System.in);
			System.out.printf(""Input equation :"");
			eq = scanner.nextLine();
		}else{
			eq = args[0];
		}
		Calculator Cal = new Calculator();
		System.out.println(Cal.ans(eq));
	}	
}
