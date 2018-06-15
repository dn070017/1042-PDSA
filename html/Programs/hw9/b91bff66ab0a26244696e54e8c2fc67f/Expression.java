public class Expression{
public Node root;
    public Expression(){}

    public Node Infix2BT(String infix){
        root= new Node(null,null,infix);
        return root;
    }
 
    public Node[] PrintPrefix(){
        Node[] prefix = new Node[2];
        prefix[0]= root;
        return prefix;
    }
  
    public Node[] PrintPostfix(){
        Node[] postfix1 = new Node[2];
        postfix1[0]= root;
        return postfix1;
    }

    public double Evaluation(){
        double answer = Double.parseDouble(root.getValue());
        return answer;  
    }
        public static void main(String[] args) {
        String input = ""(((1111111+1111111)+(11+1111))+11111)"";
        String input2 = ""(12/12)"";       
        Expression test = new Expression();
        test.Infix2BT(input);
        print(test.PrintPrefix());
        print(test.PrintPostfix()); 
        test.Infix2BT(input2);
        print(test.PrintPrefix());
        print(test.PrintPostfix());           
        System.out.println(test.Evaluation());      
    } 
        
    public static void print(Node[] pf){
        for(int i = 0; i < pf.length;i++){
            System.out.printf(pf[i].getValue()+"" "");
        }
        System.out.println();        
    }        
}

