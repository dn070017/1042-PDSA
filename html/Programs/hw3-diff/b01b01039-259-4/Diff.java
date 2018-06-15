

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;

public class Calculator {
	private Double ans;
	private String[] equation;
	private Stack<Double> num;
	private Stack<String> operator;
	private boolean tag;
	private int fst;
	
	public Calculator(){
		ans = 0.0;
		fst = 0;
		tag = false;
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
	
	private void print_stack(){
		System.out.printf(""     operator :"");
		for(String s : operator){
			System.out.printf(""%2s "", s);
		}
		System.out.printf(""\n     number :"");
		for(Double d : num){
			System.out.print(d);
			System.out.printf("" "");
		}
		System.out.println();
	}
	
	private void fin(){
		// add all numbers
		while(!operator.isEmpty() && !num.isEmpty()){
			//print_stack();
			add();
		}
		ans = num.pop();
	}
	
	private void check(Double n){
		//print_stack();
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
			}else if(o.equals(""b"")){
				//print_stack();
				//System.out.println(""B"");
				num.push(-num.pop()/n);
				operator.pop();
				operator.push(""+"");
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
	}
	
	private void first_num(Double n){
		operator.pop();
		num.push(-n);
	}
	
	private void arrange(){
		for(String s : equation){
			//print_stack();
			if(s.matches(""[0-9]+"") || s.matches(""[0-9]+.[0-9]+"")){
				tag = false;
				Double n;
				try{
					n = Double.parseDouble(s);
				}catch(Exception ex){
					n = new Double(Integer.parseInt(s));
				}
				if(fst == 2){
					first_num(n);
				}else{
					fst = 0;
					check(n);
				}
			}else if(s.matches(""[*+-/]"")){
				//System.out.println(""push "" + s);
				if(fst == 1){
					fst = 2;
				}else{
					fst = 0;
				}
				if(s.equals(""/""))tag = true;
				if(s.equals(""-"") && tag){
					operator.pop();
					operator.push(""b"");
					tag = false;
				}else{
					operator.push(s);
				}
			}else if(s.equals("")"")){
				tag = false;
				//System.out.println(""push "" + s);
				while(!operator.peek().equals(""e"")){
					add();
				}
				operator.pop();
				check(num.pop());	
			}else if(s.equals(""("")){
				//System.out.println(""push "" + s);
				tag = false;
				fst = 1;
				operator.push(""e"");
			}else{
				continue;
				//System.out.println(""Uknown input : "" + s +""."");
			} 
		}
		//System.out.printf(""Fin"");
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
			//System.out.printf(""Input equation :"");
			eq = scanner.nextLine();
		}else{
			eq = args[0];
		}
		Calculator Cal = new Calculator();
		System.out.println(Cal.ans(eq));
	}	
}


