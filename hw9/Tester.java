import java.io.FileReader;
import java.io.BufferedReader;


public class Tester{

    public static void Infix(Node node){
    	if(node == null){
    		return;
    	}
	    if(node.getLeft() != null){
            Tester.Infix(node.getLeft());
	    }
	    System.out.printf("%s ", node.getValue());
	    if(node.getRight() != null){
            Tester.Infix(node.getRight());
     	}
    }

    public static void main(String[] args) throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
        	Double type = Double.parseDouble(br.readLine());
            String in = br.readLine();
            if(type == 1){
            	Expression exp = new Expression();
            	Node root = exp.Infix2BT(in);
                Tester.Infix(root);
                System.out.println();
            }

            if(type == 2){
                Expression exp = new Expression();
                Node root = exp.Infix2BT(in);
                Node[] nodes = exp.PrintPrefix();
                for(int i = 0; i < nodes.length; i++){
                	System.out.printf("%s---", nodes[i].getValue());
                	if(nodes[i].getLeft() == null) { System.out.printf("null---"); }
                	else { System.out.printf("%s---", nodes[i].getLeft().getValue()); }
                	if(nodes[i].getRight() == null) { System.out.printf("null\n"); }
                	else { System.out.printf("%s\n", nodes[i].getRight().getValue()); }
                }
            }

            if(type == 3){
                Expression exp = new Expression();
                Node root = exp.Infix2BT(in);
                Node[] nodes = exp.PrintPostfix();
                for(int i = 0; i < nodes.length; i++){
                	System.out.printf("%s---", nodes[i].getValue());
                	if(nodes[i].getLeft() == null) { System.out.printf("null---"); }
                	else { System.out.printf("%s---", nodes[i].getLeft().getValue()); }
                	if(nodes[i].getRight() == null) { System.out.printf("null\n"); }
                	else { System.out.printf("%s\n", nodes[i].getRight().getValue()); }
                }
            }

            if(type == 4){
                Expression exp = new Expression();
                Node root = exp.Infix2BT(in);
                double ans = exp.Evaluation();
                System.out.printf("%.2f\n", ans);
            }

            if(type == 5){
                Expression exp = new Expression();
                Node root = exp.Infix2BT(in);
                double ans = exp.Evaluation();
                Node[] prefix = exp.PrintPrefix();
                Node[] postfix = exp.PrintPostfix();
                Tester.Infix(root);
                System.out.println();
                for(int i = 0; i < prefix.length; i++){
                	System.out.printf("%s ", prefix[i].getValue());
                }
                System.out.println();
                for(int i = 0; i < postfix.length; i++){
                	System.out.printf("%s ", postfix[i].getValue());
                }
                System.out.println();
                System.out.printf("%.2f\n", ans);
            }

            if(type == 0){
            	Expression exp = new Expression();
            	try{
                    Node[] prefix = exp.PrintPrefix();
            	}
            	catch(NullPointerException ex){
                    System.out.println("well done!");
            	}
            	try{
                    Node[] postfix = exp.PrintPostfix();
            	}
            	catch(NullPointerException ex){
                    System.out.println("well done!");
            	}
            	try{
                    double ans = exp.Evaluation();
            	}
            	catch(NullPointerException ex){
                    System.out.println("well done!");
            	}
            }
        }
    }
}